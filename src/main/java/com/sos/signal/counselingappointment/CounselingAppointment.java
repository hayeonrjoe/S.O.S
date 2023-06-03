package com.sos.signal.counselingappointment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
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

    public void setCa_date(LocalDateTime ca_date) {
        this.ca_date = LocalDateTime.now();
    }
}
