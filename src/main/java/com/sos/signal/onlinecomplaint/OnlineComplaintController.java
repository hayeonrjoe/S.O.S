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
        return "redirect:/online-complaint-form/submit-success";
    }

    @RequestMapping(value = "/online-complaint-form/submit-success", method = RequestMethod.GET)
    public String showSuccessPage() {
        return "online_complaint/online_complaint_form_submit_success";
    }

    @RequestMapping(value = "/online-complaint/detail", method = RequestMethod.GET)
    public String getComplaintDetail(@RequestParam("ocId") String ocId, Model model) {
        // Retrieve the complaint detail based on ocId
        OnlineComplaint complaint = onlineComplaintService.getComplaintById(Integer.parseInt(ocId));

        if (complaint != null) {
            // Add the complaint object to the model
            model.addAttribute("complaint", complaint);

            // Return the name of the HTML page for the complaint detail
            return "online_complaint/online_complaint_detail";
        } else {
            // Handle the case when the complaint is not found
            // You can redirect to an error page or return an appropriate response
            return "online_complaint/online_complaint_list";
        }
    }


    @RequestMapping(value = "/online-complaint/check-password", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Boolean> checkPassword(@RequestBody Map<String, String> requestBody) {
        Integer ocId = Integer.parseInt(requestBody.get("ocId"));
        String ocPw = requestBody.get("ocPw");

        // Retrieve the OnlineComplaint object by ocId from the database
        OnlineComplaint onlineComplaint = onlineComplaintService.findByOcId(ocId);

        // Check if the password matches
        boolean valid = (onlineComplaint != null && ocPw.equals(onlineComplaint.getOcPw()));

        // Prepare the response JSON
        Map<String, Boolean> response = new HashMap<>();
        response.put("valid", valid);

        return response;
    }

    @RequestMapping(value = "/online-complaint", method = RequestMethod.GET)
    public String showOnlineComplaintList(Model model) {

        // Fetch the latest results from the service for the specified page
        List<OnlineComplaint> latestResults = onlineComplaintService.getLatestResults();

        // Add the latest results to the model
        model.addAttribute("latestResults", latestResults);

        return "online_complaint/online_complaint_list";
    }

    @RequestMapping(value = "/online-complaint/latest-results", method = RequestMethod.GET)
    @ResponseBody
    public List<OnlineComplaint> getLatestResults() {

        // Fetch the latest results from the service for the specified page
        return onlineComplaintService.getLatestResults();

    }

    @RequestMapping(value = "/online-complaint/search", method = RequestMethod.GET)
    @ResponseBody
    public List<OnlineComplaint> searchOnlineComplaintsByTitle(@RequestParam("query") String query) {
        return onlineComplaintService.searchOnlineComplaintsByTitle(query);
    }

}