package com.sos.signal.onlinecomplaint;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class OnlineComplaintController {

    private final OnlineComplaintService onlineComplaintService;

    public OnlineComplaintController(OnlineComplaintService onlineComplaintService) {
        this.onlineComplaintService = onlineComplaintService;
    }

    @RequestMapping(value = "/online-complaint", method = RequestMethod.GET)
    public String showOnlineComplaintList(Model model) {

        List<OnlineComplaint> latestResults = onlineComplaintService.getLatestResults();

        model.addAttribute("latestResults", latestResults);

        return "online_complaint/online_complaint_list";
    }

    @RequestMapping(value = "/online-complaint/latest-results", method = RequestMethod.GET)
    @ResponseBody
    public List<OnlineComplaint> getLatestResults() {

        return onlineComplaintService.getLatestResults();

    }

    @RequestMapping(value = "/online-complaint/search", method = RequestMethod.GET)
    @ResponseBody
    public List<OnlineComplaint> searchOnlineComplaintsByTitle(@RequestParam("query") String query) {
        return onlineComplaintService.searchOnlineComplaintsByTitle(query);
    }

    @RequestMapping(value = "/online-complaint/detail", method = RequestMethod.GET)
    public String getComplaintDetail(@RequestParam("num") String num, Model model) {
        int ocId = Integer.parseInt(num);

        OnlineComplaint complaint = onlineComplaintService.getComplaintById(ocId);

        if (complaint != null) {
            model.addAttribute("complaint", complaint);

            return "online_complaint/online_complaint_detail";
        } else {
            return "online_complaint/online_complaint_list";
        }
    }

    @RequestMapping(value = "/online-complaint/check-password", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Boolean> checkPassword(@RequestBody Map<String, String> requestBody) {
        int ocId = Integer.parseInt(requestBody.get("ocId"));
        String ocPw = requestBody.get("ocPw");

        OnlineComplaint onlineComplaint = onlineComplaintService.findByOcId(ocId);

        boolean valid = (onlineComplaint != null && ocPw.equals(onlineComplaint.getOcPw()));

        Map<String, Boolean> response = new HashMap<>();
        response.put("valid", valid);

        return response;
    }

    @RequestMapping(value = "/online-complaint-form", method = RequestMethod.GET)
    public String showOnlineComplaintForm(Model model) {
        model.addAttribute("onlineComplaint", new OnlineComplaint());
        return "online_complaint/online_complaint_form";
    }

    @RequestMapping(value = "/online-complaint-form/submit", method = RequestMethod.POST)
    public String submitOnlineComplaintForm(@ModelAttribute("onlineComplaint") OnlineComplaint onlineComplaint, BindingResult bindingResult) {

        onlineComplaintService.validateOnlineComplaint(onlineComplaint, bindingResult);

        if (bindingResult.hasErrors()) {
            return "online_complaint/online_complaint_form";
        }

        onlineComplaintService.saveOnlineComplaint(onlineComplaint);

        return "redirect:/online-complaint-form/submit-success";
    }

    @RequestMapping(value = "/online-complaint-form/submit-success", method = RequestMethod.GET)
    public String showSuccessPage() {
        return "online_complaint/online_complaint_form_submit_success";
    }

}