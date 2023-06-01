package com.sos.signal.user.controller;

import com.sos.signal.user.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MemberController {

    @Autowired
    private MemberService ms;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String kakaoLogin(@RequestParam(value = "code", required = false) String code) throws Exception {
        System.out.println("#########" + code);

        String access_Token = ms.getAccessToken(code);
        System.out.println("###access_Token### : " + access_Token);

        return "member/register";
    }
}