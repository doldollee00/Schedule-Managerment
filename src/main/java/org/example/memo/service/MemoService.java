package org.example.memo.service;

import lombok.RequiredArgsConstructor;
import org.example.memo.dto.CommentResponseDto;
import org.example.memo.dto.MemoRequestDto;
import org.example.memo.dto.MemoResponseDto;
import org.example.memo.entity.Memo;
import org.example.memo.repository.MemoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    //일정 추가
    @Transactional
    public MemoResponseDto save(MemoRequestDto memoRequestDto) {
        //요청 받은 데이터로 객체 생성
        Memo memo = new Memo(memoRequestDto.getTitle(), memoRequestDto.getContent(), memoRequestDto.getName(), memoRequestDto.getPw());
        if (memoRequestDto.getPw() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The title is a required value.");
        }

        //DB에 저장
        Memo saveMemo = memoRepository.save(memo);

        return new MemoResponseDto(saveMemo.getId(), saveMemo.getTitle(), saveMemo.getContent(), saveMemo.getName(), saveMemo.getCreateAt(), saveMemo.getUpdateAt());
    }

    //전체 조회 및 이름 조회
    @Transactional(readOnly = true)
    public List<MemoResponseDto> findAll(String name) {
        List<Memo> memos;

        // 검증
        if (name == null || name.equals("")) {
            //Memo의 정보를 DB에서 찾아서 Memos에 저장
            memos = memoRepository.findAllByOrderByUpdateAtDesc();
        }else{
            //Memo의 정보 중 name을 DB에서 찾아서 Memos에 저장
            memos = memoRepository.findByNameOrderByUpdateAtDesc(name);
        }

        //List<Memo> memos = memoRepository.findAll();
        //Dto 리스트 생성 (빈 리스트)
        List<MemoResponseDto> memoResponseDtos = new ArrayList<>();
        for(Memo memo : memos) {
            memoResponseDtos.add(new MemoResponseDto(memo.getId(), memo.getTitle(), memo.getContent(), memo.getName(), memo.getCreateAt(), memo.getUpdateAt()));
        }

        return memoResponseDtos;
    }

    //단건 조회
    @Transactional(readOnly = true)
    public List<MemoResponseDto> findById(Long id) {

        Memo memo = memoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 " + id + " 입니다"));

        // 댓글 엔티티 -> DTO로 변환
        List<CommentResponseDto> commentDtos = memo.getComments().stream()
                .map(num -> new CommentResponseDto(
                        num.getCommentId(),
                        num.getComment(),
                        num.getHuman(),
                        num.getCreateAt(),
                        num.getUpdateAt()
                )).toList();

        //Dto 리스트 생성 (빈 리스트)
        List<MemoResponseDto> memoResponseDtos = new ArrayList<>();
        memoResponseDtos.add(new MemoResponseDto(memo.getId(), memo.getTitle(), memo.getContent(), memo.getName(), memo.getCreateAt(), memo.getUpdateAt(), commentDtos));
        return memoResponseDtos;
    }

    //일정 수정
    @Transactional
    public MemoResponseDto update(Long id, String title, String content, String name, Long pw) {
        //조회
        Memo memo = memoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 " + id + " 입니다"));

        //PW 검증
        if (!memo.getPw().equals(pw)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The title is a required value.");
        }

        // 필수값 검증
        if (title == null || name == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The title is a required value.");
        }

        // DB에 저장
        memo.update(title, content, name, pw);
        Memo saveMemo = memoRepository.save(memo);
        return new MemoResponseDto(memo.getId(), memo.getTitle(), memo.getContent(), memo.getName(), memo.getCreateAt(), memo.getUpdateAt());
    }

    //일정 삭제
    @Transactional()
    public ResponseEntity<Void> deleteMemo(Long id, Long pw){
        //조회
        Memo memo = memoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 " + id + " 입니다"));
        //PW 검증
        if (!memo.getPw().equals(pw)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The title is a required value.");
        }
        memoRepository.deleteById(id);
        return null;
    }
}



