package com.example.gigabox.dto;


import com.example.gigabox.users.GigaUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String content;

    //하나의 게시글(board)에 여러개의 댓글(comment) 작성 가능
    @ManyToOne
    private Qna qna;

    @ManyToOne
    private GigaUser author;
}
