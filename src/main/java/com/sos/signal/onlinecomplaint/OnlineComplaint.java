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
    private Integer aId;
    //    required
    @Column(name = "oc_pw")
    private String ocPw;

    @Column(name = "oc_title")
    private String ocTitle;

    @Column(name = "oc_content")
    private String ocContent;

    @Column(name = "oc_name")
    private String ocName;

    @Column(name = "oc_advisor")
    private String ocAdvisor;

    @Column(name = "oc_response_status", nullable = false, columnDefinition = "VARCHAR(10) DEFAULT '답변대기'")
    private String ocResponseStatus;

    //    optional
    @Column(name = "oc_contact")
    private String ocContact;

    @Column(name = "oc_date")
    private LocalDateTime ocDate;

    @Column(name = "oc_response_content")
    private String ocResponseContent;

    @Column(name = "oc_date_formatted")
    private String ocDateFormatted;

    public OnlineComplaint(String ocPw, Integer ocId, String ocTitle, String ocAdvisor, String ocName, String ocDateFormatted, String ocResponseStatus) {
        // Assign the constructor arguments to the corresponding fields
        this.ocPw = ocPw;
        this.ocId = ocId;
        this.ocTitle = ocTitle;
        this.ocAdvisor = ocAdvisor;
        this.ocName = ocName;
        this.ocDateFormatted = ocDateFormatted;
        this.ocResponseStatus = ocResponseStatus;
    }

    public OnlineComplaint(String s) {
    }

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

    public OnlineComplaint orElse(OnlineComplaint other) {
        if (this != null) {
            return this;
        } else {
            return other;
        }
    }

}