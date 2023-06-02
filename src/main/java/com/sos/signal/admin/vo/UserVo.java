package com.sos.signal.admin.vo;

import lombok.Data;

@Data
public class UserVo {
    private int id;
    private String name;
    private String contact;
    private String email;
    private String pw;
    private int admin_type;
    private String company_name;
    private String company_contact;
    private String company_address;
}
