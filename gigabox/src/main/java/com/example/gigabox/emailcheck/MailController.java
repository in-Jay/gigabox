package com.example.gigabox.emailcheck;


import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RequiredArgsConstructor
@RestController //클라이언트와 JSON, XML 등의 데이터 형식으로 통신하기 위해 사용하는 컨트롤러
public class MailController {
    private final MemberService memberService;
    private final MailService mailService;
    //메일 찾기
    @PostMapping("/check/email")
    public ResponseEntity<?> checkEmail(@RequestParam("email") String email) {
        if(!memberService.emailExist(email)){
            return new ResponseEntity<>("일치하는 메일이 없습니다.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("이메일이 확인되었습니다.", HttpStatus.OK);
    }
    //메일로 임시비밀번호 보내기
    @PostMapping("/send/password")
    public ResponseEntity<?> sendPassword(@RequestParam("email") String email){

        //임시 비밀번호 생성 및 업데이트
        String tmpPassword = memberService.getTmpPassword();
        memberService.updatePassword(tmpPassword, email);

        //메일 생성 및 발송
        MailDto mail = mailService.createMail("password", tmpPassword, email);
        try{
            mailService.sendMail(mail);
        }catch (MessagingException e) {
            e.printStackTrace();
            return new ResponseEntity<>("메일 발송 중 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("임시 비밀번호가 발급되었습니다.",HttpStatus.OK);
    }


    //회원정보 찾기
    @PostMapping("/check/user")
    public ResponseEntity<?> checkuser(@RequestParam("realname") String realname, @RequestParam("birthdate") LocalDate birthdate, @RequestParam("emailId") String email) {
        if(!memberService.userExist(realname, birthdate, email)){
            return new ResponseEntity<>("일치하는 회원이 없습니다.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("회원정보가 확인되었습니다.", HttpStatus.OK);
    }
    //메일로 아이디 보내기
    @PostMapping("/send/id")
    public ResponseEntity<?> sendId(@RequestParam("emailId") String email){
        //이메일로 회원아이디 찾기
        String userId = memberService.getUserIdByEmail(email);
        if(userId == null) {
            return new ResponseEntity<>("일치하는 아이디가 없습니다.", HttpStatus.BAD_REQUEST);
        }
        // 메일 생성 및 발송 (아이디 보내기)
        MailDto mail = mailService.createMail("id", userId, email);  // "id" 타입으로 아이디를 메일에 넣어 보냄
        try {
            mailService.sendMail(mail);
        } catch (MessagingException e) {
            e.printStackTrace();
            return new ResponseEntity<>("메일 발송 중 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("아이디가 발송되었습니다.", HttpStatus.OK);
    }


    @GetMapping("/user")
    public String sendPassword(){
        return "checkpass";
    }


}

