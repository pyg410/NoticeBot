package com.ygpark.noticeService.service;

import com.ygpark.noticeService.domain.Notice;
import com.ygpark.noticeService.service.CrawlingService.JNUCrawlingService;
import com.ygpark.noticeService.util.WebExtractionUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

// 예외처리 제대로 해서 테스트 코드 짜기(미완성)

class JNUCrawlingServiceTest {

    private final String targetUrl = "https://sw.jnu.ac.kr/sw/8250/subview.do";
    //"https://cvg.jnu.ac.kr/cvg/3608/subview.do";
    private final String forwardUrl = "https://sw.jnu.ac.kr";
    //"https://cvg.jnu.ac.kr";
    private final String dateClassName = ".td-date";

    @Test // 크롤링 테스트
    public void CrawlingTest() throws IOException {

        List<Notice> notices = JNUCrawlingService.jnuPageCrawlingNotice(targetUrl, forwardUrl);
        for (Notice n : notices) {
            System.out.println(n.getTitle() + " " + n.getUrl() + " " + n.getDate());
        }

    }

    @Test // 제목 추출 테스트
    public void getTitleTest() throws IOException{

        Document doc = Jsoup.connect(targetUrl).get();
        List<String> titleArr = WebExtractionUtil.getTitle(doc,".td-subject", "a", "strong");
        for(String s : titleArr){
            System.out.println(s);
        }
    }

    @Test
    public void getUrlTest() throws IOException{
        Document doc = Jsoup.connect(targetUrl).get();
        List<String> urlArr = WebExtractionUtil.getUrl(doc,".td-subject", forwardUrl);
        for(String s : urlArr){
            System.out.println(s);
        }
    }

    @Test
    public void getDateTest() throws IOException{
        Document doc = Jsoup.connect(targetUrl).get();
        List<String> dateArr = WebExtractionUtil.getDate(doc, dateClassName);
        for(String s : dateArr){
            System.out.println(s);
        }
    }


}