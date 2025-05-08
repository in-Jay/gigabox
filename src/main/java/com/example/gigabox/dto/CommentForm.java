package com.example.gigabox.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentForm {
    @NotEmpty(message = "내용을 입력해 주세요")
    private String content;
}
