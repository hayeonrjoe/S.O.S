package com.sos.signal.admin;

import com.sos.signal.onlinecomplaint.OnlineComplaintRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final OnlineComplaintRepository onlineComplaintRepository;

    public AdminService(AdminRepository adminRepository, OnlineComplaintRepository onlineComplaintRepository) {
        this.adminRepository = adminRepository;
        this.onlineComplaintRepository = onlineComplaintRepository;
    }

    public String validatePAdminAndGetAIdForOnlineComplaintCommentForm(String aEmail, String aPw, BindingResult bindingResult) {
        // Retrieve the Admin entity from the repository based on a_email
        Admin admin = adminRepository.findByEmail(aEmail);
        if (admin == null) {
            // Admin not found, handle the error
            return "admin/police/admin_online_complaint_comment_form_police";
        }
        // Check if the requested a_pw matches the a_pw from the repository
        if (!aPw.equals(admin.getA_pw())) {
            // a_pw doesn't match, handle the error
            bindingResult.rejectValue("a_pw", "error.a_pw", "비밀번호가 일치하지 않습니다.");
        }
        // Check if the admin is a cop in the repository
        if (!admin.getAdminType().equals("경찰")) {
            // admin is not a cop, handle the error
            bindingResult.rejectValue("adminType", "error.adminType", "관리자 형태가 맞지 않습니다.");
        }
        // Retrieve the a_id from the admin entity
        return String.valueOf(admin.getA_id());
    }

    public String getPAdminATypeForOnlineComplaintCommentForm(String adId){
        // Retrieve the Admin entity from the repository based on a_email
        Admin admin = adminRepository.findById(Integer.parseInt(adId));
        if (admin == null) {
            // Admin not found, handle the error
            return "admin/police/admin_online_complaint_comment_form_police";
        }

        // Retrieve the a_admin_type from the admin entity
        return admin.getAdminType();
    }


}
