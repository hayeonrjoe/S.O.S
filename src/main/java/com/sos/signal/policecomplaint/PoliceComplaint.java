package com.sos.signal.policecomplaint;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "police_complaint")
public class PoliceComplaint {
//    auto_increment
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pc_id")
    private Integer pc_id;
// foreign keys
    @Column(name = "a_id")
    private Integer a_id;
//    required
    private String pc_pw;
    private String pc_title;
    private String pc_content;
    private String pc_name;
    // optional
    private String pc_contact;
    // victim
    private String pc_victim_name;
    private String pc_victim_age;
    private String pc_victim_contact;
    private String pc_victim_other;
    //bully
    private String pc_bully_name;
    private String pc_bully_relationship;
    private String pc_bully_age;
    private String pc_bully_contact;
    private String pc_bully_other;
    // other info
    private LocalDateTime pc_date;
    @Column(name = "pc_response_status", nullable = false, columnDefinition = "VARCHAR(10) DEFAULT '답변대기'")
    private String pc_response_status;
    private String pc_response_content;

    private String pc_file_ids;

    public PoliceComplaint() { }

    public PoliceComplaint(String pc_pw, String pc_title, String pc_content, String pc_name) {
        this.pc_pw = pc_pw;
        this.pc_title = pc_title;
        this.pc_content = pc_content;
        this.pc_name = pc_name;
    }

    public PoliceComplaint(String pc_pw, String pc_title, String pc_content, String pc_name, String pc_contact) {
        this.pc_pw = pc_pw;
        this.pc_title = pc_title;
        this.pc_content = pc_content;
        this.pc_name = pc_name;
        this.pc_contact = pc_contact;
    }

    public PoliceComplaint(String pc_pw, String pc_title, String pc_content, String pc_name, String pc_contact, String pc_victim_name) {
        this.pc_pw = pc_pw;
        this.pc_title = pc_title;
        this.pc_content = pc_content;
        this.pc_name = pc_name;
        this.pc_contact = pc_contact;
        this.pc_victim_name = pc_victim_name;
    }

    public PoliceComplaint(String pc_pw, String pc_title, String pc_content, String pc_name, String pc_contact, String pc_victim_name, String pc_victim_age) {
        this.pc_pw = pc_pw;
        this.pc_title = pc_title;
        this.pc_content = pc_content;
        this.pc_name = pc_name;
        this.pc_contact = pc_contact;
        this.pc_victim_name = pc_victim_name;
        this.pc_victim_age = pc_victim_age;
    }

    public PoliceComplaint(String pc_pw, String pc_title, String pc_content, String pc_name, String pc_contact, String pc_victim_name, String pc_victim_age, String pc_victim_contact) {
        this.pc_pw = pc_pw;
        this.pc_title = pc_title;
        this.pc_content = pc_content;
        this.pc_name = pc_name;
        this.pc_contact = pc_contact;
        this.pc_victim_name = pc_victim_name;
        this.pc_victim_age = pc_victim_age;
        this.pc_victim_contact = pc_victim_contact;
    }

    public PoliceComplaint(String pc_pw, String pc_title, String pc_content, String pc_name, String pc_contact, String pc_victim_name, String pc_victim_age, String pc_victim_contact, String pc_victim_other) {
        this.pc_pw = pc_pw;
        this.pc_title = pc_title;
        this.pc_content = pc_content;
        this.pc_name = pc_name;
        this.pc_contact = pc_contact;
        this.pc_victim_name = pc_victim_name;
        this.pc_victim_age = pc_victim_age;
        this.pc_victim_contact = pc_victim_contact;
        this.pc_victim_other = pc_victim_other;
    }

    public PoliceComplaint(String pc_pw, String pc_title, String pc_content, String pc_name, String pc_contact, String pc_victim_name, String pc_victim_age, String pc_victim_contact, String pc_victim_other, String pc_bully_name) {
        this.pc_pw = pc_pw;
        this.pc_title = pc_title;
        this.pc_content = pc_content;
        this.pc_name = pc_name;
        this.pc_contact = pc_contact;
        this.pc_victim_name = pc_victim_name;
        this.pc_victim_age = pc_victim_age;
        this.pc_victim_contact = pc_victim_contact;
        this.pc_victim_other = pc_victim_other;
        this.pc_bully_name = pc_bully_name;
    }

    public PoliceComplaint(String pc_pw, String pc_title, String pc_content, String pc_name, String pc_contact, String pc_victim_name, String pc_victim_age, String pc_victim_contact, String pc_victim_other, String pc_bully_name, String pc_bully_relationship) {
        this.pc_pw = pc_pw;
        this.pc_title = pc_title;
        this.pc_content = pc_content;
        this.pc_name = pc_name;
        this.pc_contact = pc_contact;
        this.pc_victim_name = pc_victim_name;
        this.pc_victim_age = pc_victim_age;
        this.pc_victim_contact = pc_victim_contact;
        this.pc_victim_other = pc_victim_other;
        this.pc_bully_name = pc_bully_name;
        this.pc_bully_relationship = pc_bully_relationship;
    }

    public PoliceComplaint(String pc_pw, String pc_title, String pc_content, String pc_name, String pc_contact, String pc_victim_name, String pc_victim_age, String pc_victim_contact, String pc_victim_other, String pc_bully_name, String pc_bully_relationship, String pc_bully_age) {
        this.pc_pw = pc_pw;
        this.pc_title = pc_title;
        this.pc_content = pc_content;
        this.pc_name = pc_name;
        this.pc_contact = pc_contact;
        this.pc_victim_name = pc_victim_name;
        this.pc_victim_age = pc_victim_age;
        this.pc_victim_contact = pc_victim_contact;
        this.pc_victim_other = pc_victim_other;
        this.pc_bully_name = pc_bully_name;
        this.pc_bully_relationship = pc_bully_relationship;
        this.pc_bully_age = pc_bully_age;
    }

    public PoliceComplaint(String pc_pw, String pc_title, String pc_content, String pc_name, String pc_contact, String pc_victim_name, String pc_victim_age, String pc_victim_contact, String pc_victim_other, String pc_bully_name, String pc_bully_relationship, String pc_bully_age, String pc_bully_contact) {
        this.pc_pw = pc_pw;
        this.pc_title = pc_title;
        this.pc_content = pc_content;
        this.pc_name = pc_name;
        this.pc_contact = pc_contact;
        this.pc_victim_name = pc_victim_name;
        this.pc_victim_age = pc_victim_age;
        this.pc_victim_contact = pc_victim_contact;
        this.pc_victim_other = pc_victim_other;
        this.pc_bully_name = pc_bully_name;
        this.pc_bully_relationship = pc_bully_relationship;
        this.pc_bully_age = pc_bully_age;
        this.pc_bully_contact = pc_bully_contact;
    }

    public PoliceComplaint(String pc_pw, String pc_title, String pc_content, String pc_name, String pc_contact, String pc_victim_name, String pc_victim_age, String pc_victim_contact, String pc_victim_other, String pc_bully_name, String pc_bully_relationship, String pc_bully_age, String pc_bully_contact, String pc_bully_other) {
        this.pc_pw = pc_pw;
        this.pc_title = pc_title;
        this.pc_content = pc_content;
        this.pc_name = pc_name;
        this.pc_contact = pc_contact;
        this.pc_victim_name = pc_victim_name;
        this.pc_victim_age = pc_victim_age;
        this.pc_victim_contact = pc_victim_contact;
        this.pc_victim_other = pc_victim_other;
        this.pc_bully_name = pc_bully_name;
        this.pc_bully_relationship = pc_bully_relationship;
        this.pc_bully_age = pc_bully_age;
        this.pc_bully_contact = pc_bully_contact;
        this.pc_bully_other = pc_bully_other;
    }

    public PoliceComplaint(Integer a_id, String pc_pw, String pc_title, String pc_content, String pc_name, String pc_contact, String pc_victim_name, String pc_victim_age, String pc_victim_contact, String pc_victim_other, String pc_bully_name, String pc_bully_relationship, String pc_bully_age, String pc_bully_contact, String pc_bully_other, String pc_response_content) {
        this.a_id = a_id;
        this.pc_pw = pc_pw;
        this.pc_title = pc_title;
        this.pc_content = pc_content;
        this.pc_name = pc_name;
        this.pc_contact = pc_contact;
        this.pc_victim_name = pc_victim_name;
        this.pc_victim_age = pc_victim_age;
        this.pc_victim_contact = pc_victim_contact;
        this.pc_victim_other = pc_victim_other;
        this.pc_bully_name = pc_bully_name;
        this.pc_bully_relationship = pc_bully_relationship;
        this.pc_bully_age = pc_bully_age;
        this.pc_bully_contact = pc_bully_contact;
        this.pc_bully_other = pc_bully_other;
        this.pc_response_content = pc_response_content;
    }

    public PoliceComplaint(String pc_pw, String pc_title, String pc_content, String pc_name, String pc_contact, String pc_victim_name, String pc_victim_age, String pc_victim_contact, String pc_victim_other, String pc_bully_name, String pc_bully_relationship, String pc_bully_age, String pc_bully_contact, String pc_bully_other, LocalDateTime pc_date, String pc_response_status, String pc_response_content, String pc_file_ids) {
        this.pc_pw = pc_pw;
        this.pc_title = pc_title;
        this.pc_content = pc_content;
        this.pc_name = pc_name;
        this.pc_contact = pc_contact;
        this.pc_victim_name = pc_victim_name;
        this.pc_victim_age = pc_victim_age;
        this.pc_victim_contact = pc_victim_contact;
        this.pc_victim_other = pc_victim_other;
        this.pc_bully_name = pc_bully_name;
        this.pc_bully_relationship = pc_bully_relationship;
        this.pc_bully_age = pc_bully_age;
        this.pc_bully_contact = pc_bully_contact;
        this.pc_bully_other = pc_bully_other;
        this.pc_date = pc_date;
        this.pc_response_status = pc_response_status;
        this.pc_response_content = pc_response_content;
        this.pc_file_ids = pc_file_ids;
    }

    public PoliceComplaint(Integer a_id, String pc_pw, String pc_title, String pc_content, String pc_name, String pc_response_content) {
        this.a_id = a_id;
        this.pc_pw = pc_pw;
        this.pc_title = pc_title;
        this.pc_content = pc_content;
        this.pc_name = pc_name;
        this.pc_response_content = pc_response_content;
    }

    public Integer getPc_id() {
        return pc_id;
    }

    public void setPc_id(Integer pc_id) {
        this.pc_id = pc_id;
    }

    public Integer getA_id() {
        return a_id;
    }

    public void setA_id(Integer a_id) {
        this.a_id = a_id;
    }

    public String getPc_pw() {
        return pc_pw;
    }

    public void setPc_pw(String pc_pw) {
        this.pc_pw = pc_pw;
    }

    public String getPc_title() {
        return pc_title;
    }

    public void setPc_title(String pc_title) {
        this.pc_title = pc_title;
    }

    public String getPc_content() {
        return pc_content;
    }

    public void setPc_content(String pc_content) {
        this.pc_content = pc_content;
    }

    public String getPc_name() {
        return pc_name;
    }

    public void setPc_name(String pc_name) {
        this.pc_name = pc_name;
    }

    public String getPc_contact() {
        return pc_contact;
    }

    public void setPc_contact(String pc_contact) {
        this.pc_contact = pc_contact;
    }

    public String getPc_victim_name() {
        return pc_victim_name;
    }

    public void setPc_victim_name(String pc_victim_name) {
        this.pc_victim_name = pc_victim_name;
    }

    public String getPc_victim_age() {
        return pc_victim_age;
    }

    public void setPc_victim_age(String pc_victim_age) {
        this.pc_victim_age = pc_victim_age;
    }

    public String getPc_victim_contact() {
        return pc_victim_contact;
    }

    public void setPc_victim_contact(String pc_victim_contact) {
        this.pc_victim_contact = pc_victim_contact;
    }

    public String getPc_victim_other() {
        return pc_victim_other;
    }

    public void setPc_victim_other(String pc_victim_other) {
        this.pc_victim_other = pc_victim_other;
    }

    public String getPc_bully_name() {
        return pc_bully_name;
    }

    public void setPc_bully_name(String pc_bully_name) {
        this.pc_bully_name = pc_bully_name;
    }

    public String getPc_bully_relationship() {
        return pc_bully_relationship;
    }

    public void setPc_bully_relationship(String pc_bully_relationship) {
        this.pc_bully_relationship = pc_bully_relationship;
    }

    public String getPc_bully_age() {
        return pc_bully_age;
    }

    public void setPc_bully_age(String pc_bully_age) {
        this.pc_bully_age = pc_bully_age;
    }

    public String getPc_bully_contact() {
        return pc_bully_contact;
    }

    public void setPc_bully_contact(String pc_bully_contact) {
        this.pc_bully_contact = pc_bully_contact;
    }

    public String getPc_bully_other() {
        return pc_bully_other;
    }

    public void setPc_bully_other(String pc_bully_other) {
        this.pc_bully_other = pc_bully_other;
    }

    public LocalDateTime getPc_date() {
        return pc_date;
    }

    public void setPc_date(LocalDateTime pc_date) {
        this.pc_date = LocalDateTime.now();;
    }

    public String getPc_response_status() {
        return pc_response_status;
    }

    public void setPc_response_status(String pc_response_status) {
        this.pc_response_status = pc_response_status;
    }

    public String getPc_response_content() {
        return pc_response_content;
    }

    public void setPc_response_content(String pc_response_content) {
        this.pc_response_content = pc_response_content;
    }

    public String getPc_file_ids() {
        return pc_file_ids;
    }

    public void setPc_file_ids(String pc_file_ids) {
        this.pc_file_ids = pc_file_ids;
    }
}
