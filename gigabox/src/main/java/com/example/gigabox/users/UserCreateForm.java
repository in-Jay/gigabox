package com.example.gigabox.users;


import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class UserCreateForm {
    @Size(min = 3, max =25)
    @NotEmpty(message = "아이디를 입력해 주세요.")
    private String username;
    @NotEmpty(message = "비밀번호를 입력해 주세요.")
    private String password1;
    @NotEmpty(message = "비밀번호를 확인해 주세요.")
    private String password2;
    @NotEmpty(message = "이름을 입력해주세요.")
    private String realname;
    @NotNull(message = "생년월일을 입력해주세요")
    @Past(message = "날짜가 잘못 기입되었습니다.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;
    @Email
    @NotEmpty(message = "이메일을 입력해 주세요.")
    private String email;


}
