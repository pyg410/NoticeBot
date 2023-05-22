package com.ygpark.noticeService.service;

import com.ygpark.noticeService.domain.Notice;
import com.ygpark.noticeService.service.CrawlingService.JNUCrawlingService;
import com.ygpark.noticeService.service.CrawlingService.BusinessCrawlingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
// import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@EnableScheduling
@Slf4j
// TODO 추후 클래스이름, 메서드이름 변경 스케줄링 서비스가 SendNoticeEmail로 옮겨졌다.
public class AutoSchedulingService {

    public static String AutoScheduling(String targetUrl, String forwardUrl, String departmentName) throws IOException {
        StringBuilder content = new StringBuilder();

        List<Notice> notices = JNUCrawlingService.jnuPageCrawlingNotice(targetUrl, forwardUrl);
        log.info("--- "+ departmentName + " 크롤링을 시작합니다 ---");

        content.append("--- "+departmentName+" 공지사항 ---").append("\n");
        for (Notice n : notices){
            log.info(n.getTitle() + " " + n.getUrl() + " " + n.getDate());
            content.append(n.getTitle()).append(" ")
                    .append(n.getUrl()).append(" ")
                    .append(n.getDate()).append(" ")
                    .append("\n");
        }
        log.info("--- "+ departmentName + " 크롤링을 종료합니다 ---");

        // 공지사항이 없다면
        if(notices.isEmpty()){
            content.append("( 업데이트된 공지사항이 없습니다. )").append("\n");
        }

        content.append("\n");

        return content.toString();
    }

    //@Scheduled(cron = "0 0 8 * * ?")
    public static String AutoSchedulingSJ(String targetUrl, String forwardUrl, String behindUrl, String businessName)throws IOException{

        StringBuilder content = new StringBuilder();

        List<Notice> notices = BusinessCrawlingService.businessPageCrawlingNotice(targetUrl, forwardUrl, behindUrl);

        log.info("--- " + businessName + " 크롤링을 시작합니다 ---");
        content.append("--- "+ businessName + " 공지사항 ---").append("\n");
        for (Notice n : notices){
            log.info(n.getTitle() + " " + n.getUrl() + " " + n.getDate());
            content.append(n.getTitle()).append(" ")
                    .append(n.getUrl()).append(" ")
                    .append(n.getDate()).append(" ")
                    .append("\n");
        }
        log.info("--- "+ businessName + " 크롤링을 종료합니다. ---");

        if(notices.isEmpty()){
            content.append("( 업데이트된 공지사항이 없습니다. )").append("\n");
        }

        content.append("\n");

        return content.toString();
    }

}
