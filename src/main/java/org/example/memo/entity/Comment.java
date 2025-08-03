package org.example.memo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Comment extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long commentId;
    private String comment;
    private String human;
    private Long pw2;

    @ManyToOne
    @JoinColumn(name = "memo_id")
    private Memo memo;

    public Comment(String comment, String human, Long pw2) {

        this.comment = comment;
        this.human = human;
        this.pw2 = pw2;
    }

}
