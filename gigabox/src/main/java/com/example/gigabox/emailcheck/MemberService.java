package com.example.gigabox.emailcheck;


import com.example.gigabox.users.GigaUser;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public MemberService(MemberRepository memberRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.memberRepository = memberRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public boolean emailExist(String email) {
        return memberRepository.existsByEmail(email);
    }


    public boolean userExist(String realname, LocalDate birthdate, String emailId) { // "email" -> "emailId"
        return memberRepository.existsByRealnameAndBirthdateAndEmail(realname, birthdate, emailId);// emailId로 변경
    }

    //이메일로 회원 아이디 찾기
    public String getUserIdByEmail(String email){
        //이메일로 회원 조회
        GigaUser gigaUser = memberRepository.findByEmail(email).orElseThrow(()->new IllegalArgumentException("일치하는 회원정보가 없습니다."));

        if(gigaUser != null){
            return gigaUser.getUsername();
        }
            return null;
    }

    public String getTmpPassword() {
        char[] charSet = new char[]{ '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
                'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        String newPassword = "";

        for(int i=0; i<10; i++){
            int idx = (int)(charSet.length * Math.random());
            newPassword += charSet[idx];
        }
        return newPassword;
    }

    @Transactional
    public void updatePassword(String tmpPassword, String email) {
        System.out.println("임시 비밀번호로 업데이트 시작");

        //임시 비밀번호 암호화
        String encryptedPassword = bCryptPasswordEncoder.encode(tmpPassword);
        System.out.println("암호화된 비밀번호:" + encryptedPassword);

        //사용자 조회
        GigaUser gigaUser = memberRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다."));


        //비밀번호 업데이트
        System.out.println("기존 비밀번호:" + gigaUser.getPassword());
        gigaUser.setPassword(encryptedPassword);

        //강제저장
        memberRepository.save(gigaUser);
        System.out.println("새 비밀번호로 업데이트 완료");
    }




    @Transactional
    public boolean updatePasswordToken(String email) {
        // findByEmail을 사용하여 Member 객체를 가져옵니다.
        GigaUser gigaUser = memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다."));

        // 가져온 Member 객체에서 updatePasswordToken 호출
        return gigaUser.updatePasswordToken();
    }

}
