package com.sos.signal.admin.controller;

import com.sos.signal.admin.service.UserService;
import com.sos.signal.admin.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class adminController {

    @Autowired
    private UserService userService;
    @GetMapping("/")
    public String home(Model model) {
        int id = (int) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserVo userVo = userService.getUserById(id);
        userVo.setPassword(null);
        model.addAttribute("user", userVo);
        return "common/main";
    }

    @GetMapping("/admin_login")
    public String loginPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken)
            return "common/login_form";
        return "redirect:/";
    }

    @GetMapping("/admin_register")
    public String signup(UserVo userVo) {
        try {
            userService.signup(userVo);
        } catch (DuplicateKeyException e) {
            return "redirect:/signup?error_code=-1";
        } catch (Exception e) {
            return "redirect:/signup?error_code=-99";
        }
        return "redirect:/admin_login";
    }

    @PostMapping("/delete")
    public String withdraw(HttpSession session) {
        Integer id = (Integer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (id != null) {
            userService.withdraw(id);
        }
        SecurityContextHolder.clearContext();
        return "redirect:/";
    }

    @RequestMapping(value = "/adminregister", method = RequestMethod.GET)
    public String adminRegister() {
        return "member/register_form";
    }
}
