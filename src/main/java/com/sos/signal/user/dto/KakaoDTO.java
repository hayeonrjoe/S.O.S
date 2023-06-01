package com.sos.signal.user.dto;

import lombok.Data;

@Data
public class KakaoDTO {
    private int u_id;
    private String u_email;
    private String u_age_range;

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public String getU_email() {
        return u_email;
    }

    public void setU_email(String u_email) {
        this.u_email = u_email;
    }

    public String getU_age_range() {
        return u_age_range;
    }

    public void setU_age_range(String u_age_range) {
        this.u_age_range = u_age_range;
    }
}
