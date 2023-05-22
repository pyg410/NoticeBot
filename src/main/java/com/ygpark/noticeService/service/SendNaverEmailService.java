package com.ygpark.noticeService.service;

import com.ygpark.noticeService.admin.AdminInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
public class SendNaverEmailService {

    public static void sendEmail(String toEmail, String name, String subject) throws Exception{

        String adminEmail = AdminInfo.id+"@naver.com";

        System.out.println(AdminInfo.id);
        log.info("메일 전송 시작");
        Email email = new SimpleEmail();
        email.setHostName("smtp.naver.com");
        email.setSmtpPort(465);
        email.setCharset("euc-kr");
        email.setAuthenticator(new DefaultAuthenticator(AdminInfo.id, AdminInfo.password));
        email.setSSLOnConnect(true); // setSSL은 Deprecated 되었다고 한다.
        email.setFrom(adminEmail, "공지사항 알림봇");
        email.setSubject("[ " + LocalDate.now() + " ] 공지사항 목록입니다");
        email.setMsg(subject);
        email.addTo(toEmail, name);
        email.send();
        log.info("메일 전송 성공");
    }
}
