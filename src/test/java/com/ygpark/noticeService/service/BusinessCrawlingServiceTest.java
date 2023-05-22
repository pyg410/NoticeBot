package com.ygpark.noticeService.service;

import com.ygpark.noticeService.domain.Notice;
import com.ygpark.noticeService.service.CrawlingService.BusinessCrawlingService;
import com.ygpark.noticeService.util.WebExtractionUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;


class BusinessCrawlingServiceTest {
    private final String targetUrl ="https://www.sojoong.kr/www/notice/";
    //"https://aicoss.kr/www/notice/";
    private final String forwardUrl ="https://www.sojoong.kr/www/notice/view/";
    //"https://aicoss.kr/www/notice/view/";
    private final String behindUrl ="?bd=notice&page=1&searchOption=&searchItem=";
    // "?bd=notice&page=1&searchOption=&searchItem=&cate=";
    private final String titleClassName1 = ".alignLeft";//".cutText"
    private final String titleClassName2= ".cutText";
    private final String urlClassName = ".alignLeft";
    private final String dateClassName = ".boardInfo";

    @Test
    public void CrawlingNoticeTest() throws IOException{
        List<Notice> notices = BusinessCrawlingService.businessPageCrawlingNotice(targetUrl, forwardUrl, behindUrl);
        for (Notice n : notices){
            System.out.println(n.getTitle() + " " + n.getUrl() + " " + n.getDate());
        }
    }

    @Test // 제목 추출 테스트
    public void getTitleTest() throws IOException {

        Document doc = Jsoup.connect(targetUrl).get();
        List<String> titleArr = WebExtractionUtil.getTitle(doc,titleClassName1, titleClassName2);
        for(String s : titleArr){
            System.out.println(s);
        }
    }

    @Test
    public void getUrlTest() throws IOException{
        Document doc = Jsoup.connect(targetUrl).get();
        List<String> urlArr = WebExtractionUtil.getUrl(doc,urlClassName, forwardUrl, behindUrl);
        for(String s : urlArr){
            System.out.println(s);
        }
    }

    @Test
    public void getDateTest() throws IOException{
        Document doc = Jsoup.connect(targetUrl).get();
        List<LocalDate> dateArr = WebExtractionUtil.getDate(dateClassName, doc);
        for(LocalDate localDate : dateArr){
            System.out.println(localDate);
        }
    }

}