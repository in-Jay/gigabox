package com.example.gigabox.dto;

import com.example.gigabox.users.GigaUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.example.gigabox.dto.Comment;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
public class Qna {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String qnaType;
    private String qnaSelect;
    private String username;
    @Column(length = 200)
    private String subject;
    @Column(columnDefinition = "TEXT")
    private String content;
    private Boolean answerCheck;
    @CreationTimestamp
    private LocalDate createDate;
    private int count;
    @OneToMany(mappedBy = "qna", cascade = CascadeType.REMOVE)
    private List<Comment> commentList;

    @ManyToOne
    private GigaUser author;

    public void setAnswerCheck(Boolean answerCheck) {
        this.answerCheck = answerCheck;
    }
}
