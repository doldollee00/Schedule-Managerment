package org.example.memo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.memo.dto.MemoRequestDto;
import org.example.memo.dto.MemoResponseDto;

@Entity
@Getter
@NoArgsConstructor
public class Memo extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String name;
    private Long pw;

    public Memo(String title, String content, String name, Long pw) {
        this.title = title;
        this.content = content;
        this.name = name;
        this.pw = pw;
    }

    public void update(String title, String content, String name, Long pw) {
        this.title = title;
        this.content = content;
        this.name = name;
        this.pw = pw;
    }
}
