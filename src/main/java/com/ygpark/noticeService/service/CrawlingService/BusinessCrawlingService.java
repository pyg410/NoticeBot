package com.ygpark.noticeService.service.CrawlingService;

import com.ygpark.noticeService.domain.Notice;
import com.ygpark.noticeService.util.DaysCompare;
import com.ygpark.noticeService.util.WebExtractionUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.USER_AGENT;


@Service
@Slf4j
public class BusinessCrawlingService {
    private static final String titleClassName1 = ".alignLeft";
    private static final String titleClassName2 = ".cutText";
    private static final String urlClassName = ".alignLeft";
    private static final String dateClassName = ".boardInfo";
    private static final String categoryClassName1 = ".alignLeft";
    private static final String categoryClassName2 = ".boardCategory";

    public static List<Notice> businessPageCrawlingNotice(String targetUrl, String forwardUrl, String behindUrl) throws IOException {
        Document doc = Jsoup.connect(targetUrl).get();

        // 제목 추출
        List<String> titleArr = WebExtractionUtil.getTitle(doc, titleClassName1, titleClassName2);
        // Url 추출
        List<String> urlArr = WebExtractionUtil.getUrl(doc, urlClassName, forwardUrl, behindUrl);
        // 날짜 추출
        // List<String> dateArr = WebExtractionUtil.getDate(doc, dateClassName);
        List<LocalDate> dateArr = WebExtractionUtil.getDate(dateClassName, doc);
        // 카테고리 추출
        List<String> categoryArr = WebExtractionUtil.getTitle(doc, categoryClassName1, categoryClassName2);



        // Notice 리스트에 추출한 값 담기
        List<Notice> notices = new ArrayList<>();
        for (int i = 0; i < titleArr.size(); i++) {

            if (!categoryArr.isEmpty() && !categoryArr.get(i).equals("전남대학교")) {
                continue;
            }
            // 3일안에 올라온 공지만 체크
            // TODO isThreeDaysBefore에서 isYesterDay로 바꾸기.
            if(DaysCompare.isThreeDaysBefore(dateArr.get(i))){
                Notice notice = new Notice();
                notice.setTitle(titleArr.get(i));
                notice.setUrl(urlArr.get(i));
                notice.setDate(dateArr.get(i).toString());
                notices.add(notice);
            }


        }

        return notices.stream()
                .distinct()
                .collect(Collectors.toList());


    }

}
