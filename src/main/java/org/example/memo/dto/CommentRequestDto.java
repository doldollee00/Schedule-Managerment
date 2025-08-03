package org.example.memo.dto;

import lombok.Getter;

@Getter
public class CommentRequestDto {

    private Long memoId;
    private String comment;
    private String human;
    private Long pw2;
}
