package com.ygpark.noticeService.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SendNaverEmailServiceTest {

    @Test
    void sendEmail() throws Exception{
        SendNaverEmailService.sendEmail("pyg410@naver.com", "박영규", "test");
    }
}