package com.example.smspr3.util;

import org.springframework.stereotype.Component;
import javax.mail.*;
import javax.mail.internet.*;
import java.security.Key;
import java.util.Properties;

@Component
public class SendEmail {
    private final KeysProperties keysProperties;

    public SendEmail(
            KeysProperties keysProperties
    ) {
        this.keysProperties = keysProperties;
    }

    public void send(String to, String title, String content) throws Exception {
        String host = "smtp.gmail.com";
        String from = keysProperties.getEmail_id(); // 보내는 사람 이메일
        String password = keysProperties.getEmail_pw(); // 보내는 사람의 이메일 계정 비밀번호

        //SMTP 프로토콜 설정
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", host);
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smpt.auth", "true");
        props.setProperty("mail.smtp.starttls.enable", "true");

        //보내는 사람 계정 정보 설정
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });
        //메일 내용 작성
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(from));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        msg.setSubject(title);
        msg.setText(content);

        //메일 보내기
        Transport.send(msg);
    }
}
