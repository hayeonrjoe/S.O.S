package com.sos.signal.policecomplaint.dto;

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
@Table(name = "police_complaint")
public class PoliceComplaint {
    //    auto_increment
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pc_id")
    private Integer pcId;
    // foreign keys
    @Column(name = "a_id")
    private Integer a_id;
    //    required
    @Column(name = "pc_pw")
    private String pcPw;

    @Column(name = "pc_title")
    private String pcTitle;

    @Column(name = "pc_content")
    private String pcContent;

    @Column(name = "pc_name")
    private String pcName;
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
    @Column(name = "pc_date")
    private LocalDateTime pcDate;
    @Column(name = "pc_response_status", nullable = false, columnDefinition = "VARCHAR(10) DEFAULT '답변대기'")
    private String pcResponseStatus;
    private String pc_response_content;

    private String pc_file_ids;

    @Column(name = "pc_date_formatted")
    private String pcDateFormatted;

    public PoliceComplaint(String pcPw, Integer pcId, String pcTitle, String pcName,
                      String pcDateFormatted, String pcResponseStatus) {
        // Assign the constructor arguments to the corresponding fields
        this.pcPw = pcPw;
        this.pcId = pcId;
        this.pcTitle = pcTitle;
        this.pcName = pcName;
        this.pcDateFormatted = pcDateFormatted;
        this.pcResponseStatus = pcResponseStatus;
    }

    public void setPcDate(LocalDateTime pcDate) {
        if (pcDate == null) {
            this.pcDate = LocalDateTime.now();
        } else {
            this.pcDate = pcDate;
        }

        // Format the ocDate into a string representation
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.pcDateFormatted = this.pcDate.format(dateFormatter);
    }
}