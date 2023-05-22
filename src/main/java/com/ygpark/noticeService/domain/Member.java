package com.ygpark.noticeService.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Slf4j
// TODO Member 도메인에 Setter 없애기
public class Member {

    public Member(){}
    public Member(String email, String name) {
        this.email = email;
        this.name = name;
    }

    @Id
    @GeneratedValue
    @Column(name ="notice_id")
    private Integer id;

    private String email;
    private String name;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
