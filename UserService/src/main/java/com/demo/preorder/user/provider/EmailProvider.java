package com.demo.preorder.user.provider;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailProvider {
    private  final JavaMailSender javaMailSender;
    private final  String SUBJECT = "[예약 구매] 인증 매일입니다.";

    public boolean sendCertificationMail(String email, String certificationNumber){
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

            String htmlContent = getCertificationMessage(certificationNumber);

            messageHelper.setTo(email);
            messageHelper.setSubject(SUBJECT);
            messageHelper.setText(htmlContent, true);
            log.info("info log = emailProvider");
            log.info("info log = {}", messageHelper.getMimeMessage());
            javaMailSender.send(messageHelper.getMimeMessage());
        }catch(Exception exception){
            exception.printStackTrace();
            return false;
        }
        return true;


    }
    private String getCertificationMessage(String certificationNumber){
        String certificationMessage = "";
        certificationMessage += "<h1 style='text-align:center'>[예약 구매] 인증 메일</h1>";
        certificationMessage += "<h3 style='text-align:center'>인증코드: <strong style='font-size: 32px; letter-spacing:8px;'>"+certificationNumber+"</strong></h3>";
        return certificationMessage;
    }

    public String getCertificationNumber(){
        String certifcatinoNumber = "";

        for (int count = 0; count < 4; count++) certifcatinoNumber += (int)(Math.random()*10);

        return certifcatinoNumber;
    }
}
