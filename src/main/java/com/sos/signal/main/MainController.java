package com.sos.signal.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    @GetMapping("/main")
    public String showMain()  {
        return "common/main";
    }

    @GetMapping("/main/admin/p")
    public String showPAdminMain()  {
        return "admin/police/admin_main_police";
    }

    @GetMapping("/main/admin/c")
    public String showCAdminMain()  {
        return "admin/counselor/admin_main_counselor";
    }

    @RequestMapping(value = "/main/admin/n", method = RequestMethod.GET)
    public String showNAdminMain()  {
        return "admin/nonpolice/admin_main_nonpolice";
    }
}
