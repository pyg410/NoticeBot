package com.ygpark.noticeService.service.CrawlingService;

import com.ygpark.noticeService.domain.Notice;
import com.ygpark.noticeService.util.DaysCompare;
import com.ygpark.noticeService.util.WebExtractionUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class JNUCrawlingService {

    private static final String titleClassName = ".td-subject";
    private static final String titleTagName1 = "a";
    private static final String titleTagName2 = "strong";
    private static final String urlClassName = ".td-subject";
    private static final String dateClassName = ".td-date";
    // private final String forwardUrl = "https://cvg.jnu.ac.kr";

    // TODO 현재 SW공학과, AI융합대학 서비스 중
    public static List<Notice> jnuPageCrawlingNotice(String targetUrl, String forwardUrl) throws IOException {

        Document doc = Jsoup.connect(targetUrl).get();

        // 제목 추출
        List<String> titleArr = WebExtractionUtil.getTitle(doc, titleClassName, titleTagName1, titleTagName2);
        // Url 추출
        List<String> urlArr = WebExtractionUtil.getUrl(doc, urlClassName, forwardUrl);
        // 날짜 추출
        // List<String> dateArr = WebExtractionUtil.getDate(doc, dateClassName);
        List<LocalDate> dateArr = WebExtractionUtil.getDate(dateClassName, doc);


        // Notice 리스트에 추출한 값 담기
        List<Notice> notices = new ArrayList<>();
        for (int i=0; i<titleArr.size(); i++){
            Notice notice = new Notice();
            /* 기존 방법
            if (!titleArr.get(i).contains("새글")){
                continue;
            }
             */
            if(DaysCompare.isThreeDaysBefore(dateArr.get(i))){
                notice.setTitle(titleArr.get(i).substring(0, titleArr.get(i).length()-2));
                notice.setUrl(urlArr.get(i));
                notice.setDate(dateArr.get(i).toString());
                notices.add(notice);
            }
        }

        return notices.stream()
                .distinct()// 중복제거
                .collect(Collectors.toList());


    }
}
