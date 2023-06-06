package com.sos.signal.onlinecomplaint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    private Integer ocId;

    @Column(name = "a_id")
    private Integer a_id;
    //    required
    @Column(name = "oc_pw")
    private String ocPw;

    @Column(name = "oc_title")
    private String ocTitle;

    private String oc_content;

    @Column(name = "oc_name")
    private String ocName;

    @Column(name = "oc_advisor")
    private String ocAdvisor;

    @Column(name = "oc_response_status", nullable = false, columnDefinition = "VARCHAR(10) DEFAULT '답변대기'")
    private String ocResponseStatus;

    //    optional
    private String oc_contact;

    @Column(name = "oc_date")
    private LocalDateTime ocDate;

    private String oc_response_content;

    @Column(name = "oc_date_formatted")
    private String ocDateFormatted;

    public void setOcDate(LocalDateTime ocDate) {
        if (ocDate == null) {
            this.ocDate = LocalDateTime.now();
        } else {
            this.ocDate = ocDate;
        }

        // Format the ocDate into a string representation
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.ocDateFormatted = this.ocDate.format(dateFormatter);
    }

}