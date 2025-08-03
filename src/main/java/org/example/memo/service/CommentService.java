package org.example.memo.service;

import lombok.RequiredArgsConstructor;
import org.example.memo.dto.CommentRequestDto;
import org.example.memo.dto.CommentResponseDto;
import org.example.memo.dto.MemoResponseDto;
import org.example.memo.entity.Comment;
import org.example.memo.entity.Memo;
import org.example.memo.repository.CommentRepository;
import org.example.memo.repository.MemoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemoRepository memoRepository;

    //댓글 추가
    @Transactional
    public CommentResponseDto createComment(Long memoId, String comment, String human, Long pw2) {
        //메모 일정 조회
        Memo memo = memoRepository.findById(memoId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 " + memoId + " 입니다"));

        //요청 받은 데이터로 객체를 생성
        Comment comments = new Comment(comment, human, pw2);

        comments.setMemoComment(memo);

        if(comments.getPw2() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "비밀번호를 입력하세요");
        }
        //디비에 저장
        Comment savedComment = commentRepository.save(comments);
        return new CommentResponseDto(savedComment.getCommentId(), savedComment.getComment(), savedComment.getHuman(), savedComment.getCreateAt(), savedComment.getUpdateAt());
    }

    //댓글 삭제
    @Transactional()
    public ResponseEntity<Void> deleteComment(Long commentId){
        //조회
        commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 " + commentId + " 입니다"));

        commentRepository.deleteById(commentId);
        return null;
    }

    //전체 조회
    @Transactional(readOnly = true)
    public List<CommentResponseDto> findById(Long commentId) {
        Comment comments = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 " + commentId + " 입니다"));

        //Dto 리스트 생성 (빈 리스트)
        List<CommentResponseDto> commentResponseDtos = new ArrayList<>();
            commentResponseDtos.add(new CommentResponseDto(comments.getCommentId(), comments.getComment(), comments.getHuman(), comments.getCreateAt(), comments.getUpdateAt()));
        return commentResponseDtos;
    }

}
