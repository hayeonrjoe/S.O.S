package com.sos.signal.user.controller;

import com.sos.signal.user.dto.KakaoDTO;
import com.sos.signal.user.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
@Controller
public class MemberController {

    @Autowired
    private MemberService ms;

    @Autowired
    private HttpSession session;

    @RequestMapping(value = "/loginform", method = RequestMethod.GET)
    public String loginForm() {
        return "member/register";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String kakaoLogin(@RequestParam(value = "code", required = false) String code) throws Exception {
        System.out.println("#########" + code);
        String access_Token = ms.getAccessToken(code);
        KakaoDTO userInfo = ms.getUserInfo(access_Token);
        System.out.println("###access_Token#### : " + access_Token);
//        System.out.println("###age_range#### : " + userInfo.get("age_range"));
//        System.out.println("###email#### : " + userInfo.get("email"));

        session.invalidate();
        session.setAttribute("kakaoE", userInfo.getU_email());
        session.setAttribute("kakaoA", userInfo.getU_age_range());

        return "common/main";
    }

    @RequestMapping(value = "/chat_loginform", method=RequestMethod.GET)
    public String chatlogin() {
        return "chat/chat_login";
    }

    @RequestMapping(value = "/chat_login", method = RequestMethod.GET)
    public String chatkakaoLogin(@RequestParam(value = "code", required = false) String code) throws Exception {
//        System.out.println("#########" + code);
//        String access_Token = ms.getAccessToken(code);
//        KakaoDTO userInfo = ms.getUserInfo(access_Token);
//        System.out.println("###access_Token#### : " + access_Token);
////        System.out.println("###age_range#### : " + userInfo.get("age_range"));
////        System.out.println("###email#### : " + userInfo.get("email"));
//
//        session.invalidate();
//        session.setAttribute("kakaoE", userInfo.getU_email());
//        session.setAttribute("kakaoA", userInfo.getU_age_range());

        return "chat/room";
    }

    @RequestMapping(value = "/admin-login", method = RequestMethod.GET)
    public String adminLogin() {
        return "member/login_form";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        // 세션 초기화
        session.invalidate();

        // 로그아웃 후 리다이렉트할 페이지 또는 뷰 이름을 반환합니다.
        return "redirect:/loginform"; // 로그인 페이지로 리다이렉트하는 예시
    }

}