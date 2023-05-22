package com.ygpark.noticeService.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

// TODO Setter없애고 싱글톤패턴으로 변경하기
@Getter
@Setter
@Entity
public class Notice {


    @Id @GeneratedValue
    @Column(name ="notice_id")
    private Integer id;
    private String title;
    private String url;
    private String date;

    @Override
    public boolean equals(Object o){
        if(o instanceof Notice){
            return title.equals(((Notice) o).title);
        }
        return false;
    }
    @Override
    public int hashCode() {
        return title.hashCode();
    }

    @Override
    public String toString(){
        return title;
    }
}
