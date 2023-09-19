package com.sos.signal.admin;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdminController {

    private final AdminRepository adminRepository;

    private final AdminService adminService;

    public AdminController(AdminRepository adminRepository, AdminService adminService) {
        this.adminRepository = adminRepository;
        this.adminService = adminService;
    }

    @GetMapping("/register-form")
    public String showRegisterForm() {
        return "/member/register_form";
    }


    @GetMapping("/register-form/admin-register")
    public String showJoinForm() {
        return "/member/register_form";
    }


    @PostMapping("/signup")
    public String join(@ModelAttribute Admin admin,  @RequestParam("a_admin_type") String a_admin_type) {
        admin.setAdminType(a_admin_type);
        adminRepository.save(admin);
        return "member/login_form";
    }

    @PostMapping("/admin-signin")
    public String signIn(@RequestParam("email") String email, @RequestParam("pw") String password,
                         HttpServletRequest request) {
        List<Admin> admins = adminRepository.findMembers(email, password);
        if (!admins.isEmpty()) {
            Admin admin = admins.get(0);

            Integer aId = admin.getAId();
            admin.setAId(aId);

            Authentication authentication = new UsernamePasswordAuthenticationToken(admin, null, null);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            HttpSession session = request.getSession();
            session.setAttribute("admin", admin);

            if (admin.getAdminType().equals("경찰")) {
                return "redirect:/main/admin/p";
            } else if (admin.getAdminType().equals("상담사")){
                return "redirect:/main/admin/c";
            } else if (admin.getAdminType().equals("변호사")) {
                return "redirect:/main/admin/l";
            }
        }
        return "member/login_form";
    }

    @GetMapping("/register-form/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        SecurityContextHolder.clearContext();
        return "common/main";
    }

}
