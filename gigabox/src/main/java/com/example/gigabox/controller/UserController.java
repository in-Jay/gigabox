package com.example.gigabox.controller;

import com.example.gigabox.board.BoardForm;
import com.example.gigabox.dto.Board;
import com.example.gigabox.dto.PasswordReset;
import com.example.gigabox.service.UserService;
import com.example.gigabox.users.GigaUser;
import com.example.gigabox.users.UserCreateForm;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private final UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String signup(UserCreateForm userCreateForm){
        return "user/signup_form";
    }

    @PostMapping("/signup")
    public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
        LocalDate birthdate = userCreateForm.getBirthdate();

        if(bindingResult.hasErrors()){
            return "user/signup_form";
        }
        if(!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())){
            bindingResult.rejectValue("password2", "passwordInCorrect", "2개의 비밀번호가 일치하지 않습니다.");
            return "user/signup_form";
        }
        try {
            userService.create(userCreateForm.getUsername(), userCreateForm.getPassword1(),
                    userCreateForm.getRealname(), userCreateForm.getBirthdate(),
                    userCreateForm.getEmail());
            return "redirect:/";
        } catch (Exception e) {
            e.printStackTrace();  // 예외가 발생하면 로그에 출력
            return "user/signup_form";
        }

    }
    //로그인
    @GetMapping("/login")
    public String login(){
        return "user/login_form";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model) {
        // 1. 사용자 인증 처리 (예시: GigaUserRepository를 통해 사용자 정보 확인)
        GigaUser user = userService.getUsername(username);

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            // 로그인 성공 시 인증 정보 저장 (Spring Security는 세션을 자동으로 관리)
            // 인증 후 자동으로 로그인 처리됨 (세션에 저장)

            // 인증 성공 시 "로그인 완료"를 보여주는 홈 화면으로 리디렉션
            return "redirect:/home";  // 로그인 후 홈 화면으로 리디렉션
        } else {
            // 로그인 실패 시 오류 메시지 추가
            model.addAttribute("error", "Invalid username or password.");
            return "user/login_form";  // 로그인 실패 시 로그인 폼 페이지 다시 보여주기
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 로그아웃 처리
        // Spring Security의 SecurityContextLogoutHandler를 사용하여 로그아웃 처리
        SecurityContextHolder.clearContext(); // SecurityContext를 명시적으로 지운다.
        session.invalidate();  // 세션 무효화

        return "redirect:/login";  // 로그아웃 후 로그인 페이지로 리디렉션
    }

    //원래 작성되어 있던 내용을 불러가지고 오는 것
    //로그인해서 인증된 사용자 정보는 principal에 담긴다 그래서 이걸 가지고 오면 로그인 한 사용자의 정보를 가지고 올수 있음
    @GetMapping("/modify")
    public String modify(Model model, UserCreateForm userCreateForm, Principal principal){
        if(principal == null) {
            return "redirect:/login";
        }

        String username = principal.getName();
        GigaUser gigaUser = this.userService.getUsername(username);

        userCreateForm.setUsername(gigaUser.getUsername());
        userCreateForm.setRealname(gigaUser.getRealname());

        String formattedBirthdate = gigaUser.getBirthdate().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일"));
        userCreateForm.setEmail(gigaUser.getEmail());

        model.addAttribute("username", username);
        model.addAttribute("formattedBirthdate", formattedBirthdate);
        model.addAttribute("userCreateForm", userCreateForm);


        return "mypage/userinfo_edit";
    }

    //다시 작성한 걸 등록해야하기 떄문에 post도 만들어야함
    @PostMapping("/modify")
    public String modify(@Valid UserCreateForm userCreateForm, BindingResult bindingResult, Principal principal){
        if(bindingResult.hasErrors()){
            return "mypage/userinfo_edit";
        }
        String username = principal.getName();
        GigaUser gigaUser = this.userService.getUsername(username);

        String realname = userCreateForm.getRealname();
        String email = userCreateForm.getEmail();

        gigaUser.setRealname(realname);
        gigaUser.setEmail(email);

        userService.modify(gigaUser);
        return "redirect:/mypage";
    }

    @GetMapping("/verify")
    public String verifyPasswordForm() {
        return "mypage/verify_password"; // 비밀번호 확인 페이지로 이동
    }

    @PostMapping("/verify")
    public String verifyPassword(@RequestParam("password") String password, Principal principal) {
        // 현재 로그인된 사용자 확인
        String username = principal.getName();
        GigaUser gigaUser = userService.getUsername(username);

        // 비밀번호 검증
        if (passwordEncoder.matches(password, gigaUser.getPassword())) {
            return "redirect:/user/modify"; // 비밀번호가 맞으면 수정 페이지로 이동
        }
        if (!passwordEncoder.matches(password, gigaUser.getPassword())) {
            return "redirect:/user/verify?error=true";
        }

        // 비밀번호 틀릴 경우
        return "mypage/verify_password"; // 다시 비밀번호 확인 페이지로 이동
    }

    //아이디 중복 검사
    @GetMapping("/username_check")
    public String checkUsername(@RequestParam("username") String username, Model model) {
        boolean exists = userService.checkUsername(username);
        if(exists) {
            model.addAttribute("message", "이미 사용중인 아이디입니다.");
        }else {
            model.addAttribute("message", "사용 가능한 아이디입니다.");
        }
        return "user/username_check";
    }

    //비밀번호 재설정
    @GetMapping("/reset_pass")
    public String resetPass(){
        return "user/reset_pass";
    }

    @PostMapping("/reset_pass")
    @ResponseBody
    public String resetPass(@RequestBody PasswordReset request){
        boolean success = userService.resetPassword(request.getUsername(), request.getEmail(),
                request.getNewPassword());
        return success ? "비밀번호가 성공적으로 변경되었습니다." : "아이디와 이메일이 일치하지 않습니다.";
    }

}
