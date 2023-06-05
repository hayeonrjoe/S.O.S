package com.sos.signal.onlinecomplaint;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
public class OnlineComplaintController {

    private final OnlineComplaintService onlineComplaintService;

    public OnlineComplaintController(OnlineComplaintService onlineComplaintService){
        this.onlineComplaintService = onlineComplaintService;
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

        // Redirect to the success page
        return "redirect:/online_complaint_form/submit-success";
    }

    @RequestMapping(value = "/online_complaint_form/submit-success", method = RequestMethod.GET)
    public String showSuccessPage() {
        return "online_complaint/online_complaint_form_submit_success";
    }

}