package com.sos.signal.admin;

import com.sos.signal.onlinecomplaint.OnlineComplaintRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public boolean verifyAdminPassword(Integer aId, String password) {
        Admin admin = adminRepository.findByaId(aId);
        return admin != null && admin.getAPw().equals(password);
    }

    public String getAdminType(Integer aId) {
        String adminType = adminRepository.findAdminTypeByaId(aId);

        return adminType;
    }




}
