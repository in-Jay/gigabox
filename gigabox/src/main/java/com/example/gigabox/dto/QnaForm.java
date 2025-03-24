package com.example.gigabox.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class QnaForm {
    private String qnaType;
    @NotEmpty(message = "유형을 선택해 주세요")
    private String qnaSelect;
    @NotEmpty
    private String username;
    @NotEmpty(message = "제목을 입력해 주세요.")
    private String subject;
    @NotEmpty(message = "내용을 입력해 주세요.")
    private String content;
    private MultipartFile file;
}
