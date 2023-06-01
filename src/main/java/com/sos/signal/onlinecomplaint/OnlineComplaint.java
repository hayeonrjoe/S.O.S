package com.sos.signal.onlinecomplaint;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "online_complaint")
public class OnlineComplaint {
//    auto_increment
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "oc_id")
    private Integer oc_id;

    @Column(name = "a_id")
    private Integer a_id;
//    required
    private String oc_pw;
    private String oc_title;
    private String oc_content;
    private String oc_name;
    private String oc_advisor;
    @Column(name = "oc_response_status", nullable = false, columnDefinition = "VARCHAR(10) DEFAULT '답변대기'")
    private String oc_response_status;
    //    optional
    private String oc_contact;
    private LocalDateTime oc_date;
    private String oc_response_content;

    public OnlineComplaint() { }

    public OnlineComplaint(String oc_pw, String oc_name, String oc_advisor, String oc_title, String oc_content, String oc_response_status) {
        this.oc_pw=oc_pw;
        this.oc_name=oc_name;
        this.oc_advisor=oc_advisor;
        this.oc_title=oc_title;
        this.oc_content=oc_content;
        this.oc_response_status=oc_response_status;
    }

    public OnlineComplaint(String oc_pw, String oc_name, String oc_advisor, String oc_title, String oc_content, String oc_response_status, LocalDateTime oc_date) {
        this.oc_pw=oc_pw;
        this.oc_name=oc_name;
        this.oc_advisor=oc_advisor;
        this.oc_title=oc_title;
        this.oc_content=oc_content;
        this.oc_response_status=oc_response_status;
        this.oc_date=oc_date;
    }

    public OnlineComplaint(String oc_pw, String oc_name, String oc_advisor, String oc_title, String oc_content, String oc_response_status, LocalDateTime oc_date, String oc_contact) {
        this.oc_pw=oc_pw;
        this.oc_name=oc_name;
        this.oc_advisor=oc_advisor;
        this.oc_title=oc_title;
        this.oc_content=oc_content;
        this.oc_response_status=oc_response_status;
        this.oc_date=oc_date;
        this.oc_contact=oc_contact;
    }

    public OnlineComplaint(String oc_pw, String oc_name, String oc_advisor, String oc_title, String oc_content, String oc_response_status, LocalDateTime oc_date, String oc_contact, String oc_response_content) {
        this.oc_pw=oc_pw;
        this.oc_name=oc_name;
        this.oc_advisor=oc_advisor;
        this.oc_title=oc_title;
        this.oc_content=oc_content;
        this.oc_response_status=oc_response_status;
        this.oc_date=oc_date;
        this.oc_contact=oc_contact;
        this.oc_response_content=oc_response_content;
    }

    public Integer getOc_id() {
        return oc_id;
    }

    public void setOc_id(Integer oc_id) {
        this.oc_id = oc_id;
    }

    public Integer getA_id() {
        return a_id;
    }

    public void setA_id(Integer a_id) {
        this.a_id = a_id;
    }

    public String getOc_pw() {
        return oc_pw;
    }

    public void setOc_pw(String oc_pw) {
        this.oc_pw = oc_pw;
    }

    public String getOc_title() {
        return oc_title;
    }

    public void setOc_title(String oc_title) {
        this.oc_title = oc_title;
    }

    public String getOc_content() {
        return oc_content;
    }

    public void setOc_content(String oc_content) {
        this.oc_content = oc_content;
    }

    public String getOc_name() {
        return oc_name;
    }

    public void setOc_name(String oc_name) {
        this.oc_name = oc_name;
    }

    public String getOc_advisor() {
        return oc_advisor;
    }

    public void setOc_advisor(String oc_advisor) {
        this.oc_advisor = oc_advisor;
    }

    public String getOc_response_status() {
        return oc_response_status;
    }

    public void setOc_response_status(String oc_response_status) {
        this.oc_response_status = oc_response_status;
    }

    public String getOc_contact() {
        return oc_contact;
    }

    public void setOc_contact(String oc_contact) {
        this.oc_contact = oc_contact;
    }

    public LocalDateTime getOc_date() {
        return oc_date;
    }

    public void setOc_date(LocalDateTime oc_date) {
        this.oc_date = LocalDateTime.now();
    }

    public String getOc_response_content() {
        return oc_response_content;
    }

    public void setOc_response_content(String oc_response_content) {
        this.oc_response_content = oc_response_content;
    }
}
