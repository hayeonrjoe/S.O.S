package com.sos.signal.information;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/information")
public class informationController{
    @GetMapping("/school_violence")
    public String School() {
        return "information/school_violence";
    }

    @GetMapping("/reference_case")
    public String reference() {
        return "/information/reference_case";
    }

    @GetMapping("/legal_information")
    public String legal() {
        return "/information/legal_information";
    }

    @GetMapping("/report_penalty")
    public String report() {
        return "/information/report_penalty";
    }

    @GetMapping("/reference_case_detail")
    public String referencedetail() {
        return "/information/reference_case_detail";
    }

    @GetMapping("/legal_information_detail")
    public String legaldetail() {
        return "/information/legal_information_detail";
    }
}