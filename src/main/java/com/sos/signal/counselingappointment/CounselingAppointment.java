package com.sos.signal.counselingappointment;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "counseling_appointment")
public class CounselingAppointment {
    //    auto_increment
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ca_id")
    private Integer ca_id;
    // all required
    private String ca_name;
    private String ca_contact;
    private String ca_area;
    private String ca_place;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime ca_scheduled_date;
    @Column(name = "ca_response_status", nullable = false, columnDefinition = "VARCHAR(10) DEFAULT '답변대기'")
    private String ca_response_status;

    private LocalDateTime ca_date;

    public CounselingAppointment() {}
    public CounselingAppointment(String ca_name, String ca_contact, String ca_area, String ca_place, LocalDateTime ca_scheduled_date, String ca_response_status, LocalDateTime ca_date) {
        this.ca_name = ca_name;
        this.ca_contact = ca_contact;
        this.ca_area = ca_area;
        this.ca_place = ca_place;
        this.ca_scheduled_date = ca_scheduled_date;
        this.ca_response_status = ca_response_status;
        this.ca_date = ca_date;
    }

    public Integer getCa_id() {
        return ca_id;
    }

    public void setCa_id(Integer ca_id) {
        this.ca_id = ca_id;
    }

    public String getCa_name() {
        return ca_name;
    }

    public void setCa_name(String ca_name) {
        this.ca_name = ca_name;
    }

    public String getCa_contact() {
        return ca_contact;
    }

    public void setCa_contact(String ca_contact) {
        this.ca_contact = ca_contact;
    }

    public String getCa_area() {
        return ca_area;
    }

    public void setCa_area(String ca_area) {
        this.ca_area = ca_area;
    }

    public String getCa_place() {
        return ca_place;
    }

    public void setCa_place(String ca_place) {
        this.ca_place = ca_place;
    }

    public LocalDateTime getCa_scheduled_date() {
        return ca_scheduled_date;
    }

    public void setCa_scheduled_date(LocalDateTime ca_scheduled_date) {
        this.ca_scheduled_date = ca_scheduled_date;
    }

    public String getCa_response_status() {
        return ca_response_status;
    }

    public void setCa_response_status(String ca_response_status) {
        this.ca_response_status = ca_response_status;
    }

    public LocalDateTime getCa_date() {
        return ca_date;
    }

    public void setCa_date(LocalDateTime ca_date) {
        this.ca_date = LocalDateTime.now();;
    }
}
