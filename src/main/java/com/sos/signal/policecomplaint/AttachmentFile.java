package com.sos.signal.policecomplaint;

import javax.persistence.*;

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

    public AttachmentFile() {
    }

    public AttachmentFile(String af_file_name, String af_upload_path) {
        this.af_file_name = af_file_name;
        this.af_upload_path = af_upload_path;
    }

    public Integer getPc_file_id() {
        return pc_file_id;
    }

    public void setPc_file_id(Integer pc_file_id) {
        this.pc_file_id = pc_file_id;
    }

    public String getAf_file_name() {
        return af_file_name;
    }

    public void setAf_file_name(String af_file_name) {
        this.af_file_name = af_file_name;
    }

    public String getAf_upload_path() {
        return af_upload_path;
    }

    public void setAf_upload_path(String af_upload_path) {
        this.af_upload_path = af_upload_path;
    }
}
