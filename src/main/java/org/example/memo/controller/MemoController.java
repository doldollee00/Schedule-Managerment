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
@RequestMapping("/memo")
public class MemoController {

    private final MemoService memoService;

    @PostMapping
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto memoRequestDto) {
        return memoService.save(memoRequestDto);
    }

    @GetMapping
    public List<MemoResponseDto> getMemo(@RequestParam(required = false) String name) {
        return memoService.findAll(name);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeno(@PathVariable Long id, @RequestBody MemoRequestDto pw) {
       return memoService.deleteMemo(id, pw.getPw());
    }

    @GetMapping("/{id}")
    public List<MemoResponseDto> getMemoById(@PathVariable Long id) {
        return memoService.findById(id);
    }

    @PatchMapping("/{id}")
    public MemoResponseDto updateByMemo(@PathVariable Long id, @RequestBody MemoRequestDto dto) {
        return memoService.update(id, dto.getTitle(), dto.getContent(), dto.getName(), dto.getPw());
    }
}
