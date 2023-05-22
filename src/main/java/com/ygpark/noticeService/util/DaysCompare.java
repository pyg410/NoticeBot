package com.ygpark.noticeService.util;

import java.time.LocalDate;

public class DaysCompare {

    public static boolean isThreeDaysBefore(LocalDate localDate){

        return LocalDate.now().minusDays(4).isBefore(localDate);
    }

    public static boolean isYesterday(LocalDate localDate){
        // 2일을 빼주어야 어제 공지사항까지 확인가능
        return LocalDate.now().minusDays(2).isBefore(localDate);
    }
}
