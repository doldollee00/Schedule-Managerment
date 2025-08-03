package org.example.memo.service;

import lombok.RequiredArgsConstructor;
import org.example.memo.dto.CommentRequestDto;
import org.example.memo.dto.CommentResponseDto;
import org.example.memo.entity.Comment;
import org.example.memo.entity.Memo;
import org.example.memo.repository.CommentRepository;
import org.example.memo.repository.MemoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemoRepository memoRepository;
    int count = 0;

    //댓글 추가
    @Transactional
    public CommentResponseDto createComment(Long memoId, String comment, String human, Long pw2) {
        if(count < 10){
            //메모 일정 조회
            Memo memo = memoRepository.findById(memoId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 " + memoId + " 입니다"));

            //요청 받은 데이터로 객체를 생성
            Comment comments = new Comment(comment, human, pw2);

            if(comments.getPw2() == null){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "비밀번호를 입력하세요");
            }
            //디비에 저장
            Comment savedComment = commentRepository.save(comments);
            count ++;
            return new CommentResponseDto(savedComment.getCommentId(), savedComment.getComment(), savedComment.getHuman(), savedComment.getCreateAt(), savedComment.getUpdateAt());
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "댓글이 너무 많습니다.");
        }
    }

    //댓글 삭제
    @Transactional()
    public ResponseEntity<Void> deleteComment(Long commentId){
        //조회
        Comment savedComment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 " + commentId + " 입니다"));

        commentRepository.deleteById(commentId);
        return null;
    }

}
