package org.example.memo.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class CommentResponseDto {

    private final Long commentId;
    private final String comment;
    private final String human;
    private final LocalDateTime commentDate;
    private final LocalDateTime updateDate;
}
