package com.ygpark.noticeService.controller;

import com.ygpark.noticeService.domain.Member;
import com.ygpark.noticeService.service.AutoSchedulingService;
import com.ygpark.noticeService.service.SendNaverEmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@EnableScheduling
public class AutoSendNoticeEmail {


    @Scheduled(cron = "0 0 9 * * ?")
    public static void sendNotice() throws Exception {
        // TODO 임시 테스트 데이터(추후 DB에서 받아오게 설정)
        List<Member> memberList = new ArrayList<>();
        memberList.add(new Member("ghtyu21@naver.com", "토니"));
        memberList.add(new Member("beth0929@naver.com", "또리"));
        memberList.add(new Member("pon05114@naver.com", "쿠키"));
        memberList.add(new Member("kky1373@naver.com", "여리"));
        memberList.add(new Member("minl741@naver.com","몰리"));
        memberList.add(new Member("yugio_@naver.com","박태현"));

        String aiContent = AutoSchedulingService.AutoScheduling("https://cvg.jnu.ac.kr/cvg/3608/subview.do", "https://cvg.jnu.ac.kr", "AI융합대학");
        String swContent = AutoSchedulingService.AutoScheduling("https://sw.jnu.ac.kr/sw/8250/subview.do", "https://sw.jnu.ac.kr", "SW공학과");
        String sjContent = AutoSchedulingService.AutoSchedulingSJ("https://www.sojoong.kr/www/notice/", "https://www.sojoong.kr/www/notice/view/", "?bd=notice&page=1&searchOption=&searchItem=", "소중사업단");
        String aiCossContent = AutoSchedulingService.AutoSchedulingSJ("https://aicoss.kr/www/notice/","https://aicoss.kr/www/notice/view/","?bd=notice&page=1&searchOption=&searchItem=&cate=", "AICOSS");

        memberList.forEach(member -> {
            try {
                SendNaverEmailService.sendEmail(member.getEmail(), member.getName(), aiContent + swContent + sjContent +aiCossContent);
            } catch (Exception e) {
                log.error("AutoMailingServiceError" + e);
            }
        });
    }
}
