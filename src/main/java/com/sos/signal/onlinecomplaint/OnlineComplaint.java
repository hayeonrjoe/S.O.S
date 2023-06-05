package com.sos.signal.onlinecomplaint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
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

    public void setOc_date(LocalDateTime oc_date) {
        this.oc_date = LocalDateTime.now();
    }
}