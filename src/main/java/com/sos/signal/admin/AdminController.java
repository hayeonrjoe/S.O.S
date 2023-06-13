package com.sos.signal.admin;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class AdminController {

    private final AdminRepository adminRepository;

    private final AdminService adminService;

    public AdminController(AdminRepository adminRepository, AdminService adminService) {
        this.adminRepository = adminRepository;
        this.adminService = adminService;
    }

    @RequestMapping("/register-form")
    public String showRegisterForm() {
        return "/member/register_form";
    }


    @RequestMapping(value = "/register-form/admin-register", method = RequestMethod.GET)
    public String showJoinForm() {
        return "/member/register_form";
    }


    @PostMapping("/signup")
    public String join(@ModelAttribute Admin admin,  @RequestParam("a_admin_type") String a_admin_type) {
        admin.setAdminType(a_admin_type);
        adminRepository.save(admin);
        return "member/login_form";
    }

    @RequestMapping(value = "/admin-signin", method = RequestMethod.POST)
    public String signIn(@RequestParam("email") String email, @RequestParam("pw") String password,
                         HttpServletRequest request) {
        List<Admin> admins = adminRepository.findMembers(email, password);
        if (!admins.isEmpty()) {
            Admin admin = admins.get(0);
            // Store the aId in the admin object for future reference
            Integer aId = admin.getAId();
            admin.setAId(aId);

            Authentication authentication = new UsernamePasswordAuthenticationToken(admin, null, null);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Store the admin in the HTTP session
            HttpSession session = request.getSession();
            session.setAttribute("admin", admin);

            if (admin.getAdminType().equals("경찰")) {
                return "redirect:/main/admin/p";
            } else {
                return "redirect:/main/admin/n";
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

    @GetMapping("/admin/get-admin-type")
    @ResponseBody
    public String getAdminType(@RequestParam("aId") Integer aId) {
        // Retrieve the adminType based on the aId from your database or other data source
        String adminType = adminService.getAdminType(aId);

        return adminType;
    }

}
