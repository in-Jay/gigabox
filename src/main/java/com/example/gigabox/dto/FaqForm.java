package com.example.gigabox.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
public class FaqForm {
    @NotEmpty(message = "제목을 입력해 주세요.")
    private String category;
    @NotEmpty(message = "질문을 입력해 주세요.")
    private String question;
    @NotEmpty(message = "내용을 입력해 주세요.")
    private String answer;
}
