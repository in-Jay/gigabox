package com.example.gigabox.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BoardForm {
    //유효성 검사니까 비어있으면 안돼(낫엠티) -> 비어있으면 메시지 나와라 설정
    @NotEmpty(message = "제목을 입력해 주세요.")
    @Size(max=200)
    private String subject;
    @NotEmpty(message = "내용을 입력해 주세요")
    private String content;
}
