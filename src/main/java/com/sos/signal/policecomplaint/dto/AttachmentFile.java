package com.sos.signal.policecomplaint.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "attachment_file")
public class AttachmentFile {
//    auto_increment
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pc_file_id")
    private Integer pc_file_id;

    private String af_file_name;
    private String af_upload_path;
}
