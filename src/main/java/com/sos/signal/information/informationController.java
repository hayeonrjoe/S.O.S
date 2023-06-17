package com.sos.signal.information;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/information")
public class informationController{
    @GetMapping("/school-violence")
    public String school() {
        return "information/school_violence";
    }

    @GetMapping("/reference-case")
    public String reference() {
        return "/information/reference_case";
    }

    @GetMapping("/legal-information")
    public String legal() {
        return "/information/legal_information";
    }

    @GetMapping("/report-penalty")
    public String report() {
        return "/information/report_penalty";
    }

    @GetMapping("/reference-case-detail")
    public String referenceDetail() {
        return "/information/reference_case_detail";
    }

    @GetMapping("/legal-information-detail")
    public String legalDetail() {
        return "/information/legal_information_detail";
    }
}