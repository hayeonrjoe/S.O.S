package com.sos.signal.policecomplaint.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;

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

    public void setPc_date(LocalDateTime pc_date) {
        this.pc_date = LocalDateTime.now();
    }
}