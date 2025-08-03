package org.example.memo.controller;

import lombok.RequiredArgsConstructor;
import org.example.memo.dto.CommentRequestDto;
import org.example.memo.dto.CommentResponseDto;
import org.example.memo.dto.MemoRequestDto;
import org.example.memo.dto.MemoResponseDto;
import org.example.memo.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/memos")
public class CommentController {
    private final CommentService commentService;

    //댓글 작성
    @PostMapping("/{memoId}")
    public CommentResponseDto createComment(@PathVariable Long memoId, @RequestBody CommentRequestDto dto) {
        return commentService.createComment(memoId, dto.getComment(), dto.getHuman(), dto.getPw2());
    }

    //댓글 삭제
    @DeleteMapping("/{memoID}/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        return commentService.deleteComment(commentId);
    }

    //일정 검색(사용자 명으로도 검색 가능)
    @GetMapping("/{memoID}/{commentId}")
    public List<CommentResponseDto> getComment(@PathVariable Long commentId) {
        return commentService.findById(commentId);
    }
}
