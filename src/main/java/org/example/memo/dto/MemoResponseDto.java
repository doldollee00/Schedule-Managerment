package org.example.memo.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.memo.entity.Memo;

import java.time.LocalDateTime;
import java.util.List;

@Getter
//@RequiredArgsConstructor
public class MemoResponseDto {

    private final Long id;
    private final String title;
    private final String content;
    private final String name;
    private final LocalDateTime createAt;
    private final LocalDateTime updateAt;
    private final List<CommentResponseDto> comments;

    public MemoResponseDto(Long id, String title, String content, String name, LocalDateTime createAt, LocalDateTime updateAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.name = name;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.comments = null; // 댓글 없음!
    }

    public MemoResponseDto(Long id, String title, String content, String name, LocalDateTime createAt, LocalDateTime updateAt, List<CommentResponseDto> comments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.name = name;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.comments = comments;
    }
}

