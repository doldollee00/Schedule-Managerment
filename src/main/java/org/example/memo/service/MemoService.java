package org.example.memo.service;

import lombok.RequiredArgsConstructor;
import org.example.memo.dto.MemoRequestDto;
import org.example.memo.dto.MemoResponseDto;
import org.example.memo.entity.Memo;
import org.example.memo.repository.MemoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    @Transactional
    public MemoResponseDto save(MemoRequestDto memoRequestDto) {
        //요청 받은 데이터로 객체 생성
        Memo memo = new Memo(memoRequestDto.getTitle(), memoRequestDto.getContent(), memoRequestDto.getName(), memoRequestDto.getPw());
        //DB에 저장
        Memo saveMemo = memoRepository.save(memo);

        return new MemoResponseDto(saveMemo.getId(), saveMemo.getTitle(), saveMemo.getContent(), saveMemo.getName(), saveMemo.getCreateAt(), saveMemo.getUpdateAt());
    }

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

    @Transactional()
    public ResponseEntity<Void> deleteMemo(Long id){

        memoRepository.deleteById(id);
        return null;
    }

    /*  Lv2 작성자명으로 조회하는 것으로 착각하여 구현
    @Transactional(readOnly = true)
    public List<MemoResponseDto> findByName(String name) {
        //Memo의 정보를 DB에서 찾아서 Memos에 저장
        List<Memo> memos = memoRepository.findByName(name);
        //Dto 리스트 생성 (빈 리스트)
        List<MemoResponseDto> memoResponseDtos = new ArrayList<>();

        for(Memo memo : memos) {
            memoResponseDtos.add(new MemoResponseDto(memo.getId(), memo.getTitle(), memo.getContent(), memo.getName(), memo.getCreateAt(), memo.getUpdateAt()));
        }
        return memoResponseDtos;
    }
     */
}


