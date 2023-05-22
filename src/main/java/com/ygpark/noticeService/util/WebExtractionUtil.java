package com.ygpark.noticeService.util;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WebExtractionUtil {

    public static List<String> getTitle(Document document, String cssQuery){

        List<String> titleArr = new ArrayList<>();
        Elements elements = document.select(cssQuery);

        for(Element element : elements){
            String title = element.text();
            titleArr.add(title);
        }

        return titleArr;
    }
    public static List<String> getTitle(Document document, String cssQuery1, String cssQuery2){

        List<String> titleArr = new ArrayList<>();
        Elements elements = document.select(cssQuery1).select(cssQuery2);

        for(Element element : elements){
            String title = element.text();
            titleArr.add(title);
        }

        return titleArr;
    }
    public static List<String> getTitle(Document document, String cssQuery1, String cssQuery2, String cssQuery3){

        List<String> titleArr = new ArrayList<>();
        Elements elements = document.select(cssQuery1).select(cssQuery2).select(cssQuery3);

        for(Element element : elements){
            String title = element.text();
            titleArr.add(title);
        }

        return titleArr;
    }

    public static List<String> getUrl(Document document, String cssQuery, String forwardUrl){

        Elements elements = document.select(cssQuery);
        List<String> urlArr = new ArrayList<>();

        for(Element element : elements){

            String url = element.getElementsByAttribute("href").toString();

            int index = url.indexOf("\"");
            url = url.substring(index+1);
            index = url.indexOf("\"");
            url = url.substring(0,index);
            url = forwardUrl + url;

            urlArr.add(url);
        }

        return urlArr;

    }

    public static List<String> getUrl(Document document, String cssQuery, String forwardUrl, String behindUrl){

        Elements elements = document.select(cssQuery);
        List<String> urlArr = new ArrayList<>();

        for(Element element : elements){

            // href="javascript:movePageView(277);"
            String url = element.getElementsByAttribute("href").toString();

            int index = url.indexOf("(");
            url = url.substring(index+1);
            index = url.indexOf(")");
            url = url.substring(0,index);
            url = forwardUrl + url + behindUrl;

            urlArr.add(url);
        }

        return urlArr;

    }

    public static List<String> getDate(Document document, String cssQuery){

        Elements elements = document.select(cssQuery);
        List<String> dateArr = new ArrayList<>();

        for(Element element : elements){

            String date = element.text();
            dateArr.add(date);

        }

        return dateArr;
    }
    // TODO dateArr 변수명 변경
    public static List<LocalDate> getDate(String cssQuery, Document document){

        Elements elements = document.select(cssQuery);

        List<LocalDate> dateArr = elements.stream()
                .map(Element::text)
                .map(strE -> strE.replace(".", ""))
                .map(s -> LocalDate.parse(s, DateTimeFormatter.BASIC_ISO_DATE))
                .collect(Collectors.toList());

        return dateArr;

    }

    public static List<String> getDate(Document document, String cssQuery1, String cssQuery2){

        Elements elements = document.select(cssQuery1).select(cssQuery2);
        List<String> dateArr = new ArrayList<>();

        for(Element element : elements){

            String date = element.text();
            dateArr.add(date);

        }

        return dateArr;
    }
}
