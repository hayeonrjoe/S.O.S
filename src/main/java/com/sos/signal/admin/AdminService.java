package com.sos.signal.admin;

import com.sos.signal.onlinecomplaint.OnlineComplaintRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public boolean verifyAdminPassword(String aEmail, String password) {
        Admin admin = adminRepository.findByaEmail(aEmail);
        return admin != null && admin.getAPw().equals(password);
    }

    public Integer getAdminIdByEmail(String aEmail) {
        Admin admin = adminRepository.findByaEmail(aEmail);

        if (admin != null) {
            return admin.getAId();
        } else {
            return null;
        }
    }
}
