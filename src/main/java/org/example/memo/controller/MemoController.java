package org.example.memo.controller;

import lombok.RequiredArgsConstructor;
import org.example.memo.dto.MemoRequestDto;
import org.example.memo.dto.MemoResponseDto;
import org.example.memo.service.MemoService;
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
    public List<MemoResponseDto> getMemo() {
        return memoService.findAll();
    }

    @GetMapping("/{name}")
    public List<MemoResponseDto> getMemberByName(@PathVariable String name) {
        return memoService.findByName(name);
    }
}
