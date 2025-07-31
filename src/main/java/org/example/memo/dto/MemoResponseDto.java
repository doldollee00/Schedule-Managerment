package org.example.memo.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class MemoResponseDto {

    private final Long id;
    private final String title;
    private final String content;
    private final String name;
    private final LocalDateTime createAt;
    private final LocalDateTime updateAt;

}
