package org.example.memo.controller;

import lombok.RequiredArgsConstructor;
import org.example.memo.dto.MemoRequestDto;
import org.example.memo.dto.MemoResponseDto;
import org.example.memo.entity.Memo;
import org.example.memo.service.MemoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/memos")
public class MemoController {

    private final MemoService memoService;

    //일정 저장
    @PostMapping
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto memoRequestDto) {
        return memoService.save(memoRequestDto);
    }

    //일정 검색(사용자 명으로도 검색 가능)
    @GetMapping
    public List<MemoResponseDto> getMemo(@RequestParam(required = false) String name) {
        return memoService.findAll(name);
    }

    //일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeno(@PathVariable Long id, @RequestBody MemoRequestDto pw) {
       return memoService.deleteMemo(id, pw.getPw());
    }

    //일정 검색(Id로)
    @GetMapping("/{id}")
    public List<MemoResponseDto> getMemoById(@PathVariable Long id) {
        return memoService.findById(id);
    }

    //일정 수정
    @PatchMapping("/{id}")
    public MemoResponseDto updateByMemo(@PathVariable Long id, @RequestBody MemoRequestDto dto) {
        return memoService.update(id, dto.getTitle(), dto.getContent(), dto.getName(), dto.getPw());
    }
}
