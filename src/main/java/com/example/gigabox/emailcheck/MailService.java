package com.example.gigabox.emailcheck;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {
    @Value("${spring.mail.username}")
    private String from;

    private final JavaMailSender mailSender;
    private static final String passwordTitle = "임시 비밀번호 안내드립니다.";
    private static final String passwordMessage = "안녕하세요." + "<br><br>" + "기가박스 고객센터 입니다." + "<br>" +"회원님의 임시 비밀번호는 아래와 같습니다." + "<br>" + "로그인 후 반드시 비밀번호를 변경해주시기 바랍니다.";

    private static final String idTitle = "회원 아이디 안내드립니다.";
    private static final String idMessage = "안녕하세요." + "<br><br>" + "기가박스 고객센터 입니다." + "<br>" +"회원님의 아이디는 '%s' 입니다." + "<br>" + "이용에 참고하시길 바랍니다.";

    // 이메일 유형에 따른 메일을 생성하는 메서드
    public MailDto createMail(String type, String info, String to) {
        String title;
        String message;


        // 이메일 유형에 따라 제목과 메시지를 다르게 설정
        if ("password".equals(type)) {
            title = passwordTitle;
            message = passwordMessage + "<br>" + info;  // 임시 비밀번호
        } else if ("id".equals(type)) {
            title = idTitle;
            message = String.format(idMessage, info);  // 아이디
        } else {
            throw new IllegalArgumentException("잘못된 이메일 유형입니다.");
        }

        return new MailDto(from, to, title, message);
    }

    public void sendMail(MailDto mailDto) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        helper.setTo(mailDto.getTo());
        helper.setSubject(mailDto.getTitle());
        helper.setText(mailDto.getMessage(), true); // true를 설정해야 HTML로 전송됨
        helper.setFrom(mailDto.getFrom());
        helper.setReplyTo(mailDto.getFrom());

        mailSender.send(mimeMessage);
    }
}
