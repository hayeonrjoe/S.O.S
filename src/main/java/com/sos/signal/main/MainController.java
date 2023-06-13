package com.sos.signal.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String showMain()  {
        return "common/main";
    }

    @RequestMapping(value = "/main/admin/p", method = RequestMethod.GET)
    public String showPAdminMain()  {
        return "admin/police/admin_main_police";
    }

    @RequestMapping(value = "/main/admin/n", method = RequestMethod.GET)
    public String showNAdminMain()  {
        return "admin/nonpolice/admin_main_nonpolice";
    }
}
