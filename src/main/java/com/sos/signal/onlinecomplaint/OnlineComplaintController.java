package com.sos.signal.onlinecomplaint;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


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

    @RequestMapping(value = "/online-complaint", method = RequestMethod.GET)
    public String showOnlineComplaintList(Model model) {
        model.addAttribute("onlineComplaint", new OnlineComplaint());
        return "online_complaint/online_complaint_list";
    }

    @RequestMapping(value = "/online-complaint/search", method = RequestMethod.GET)
    @ResponseBody
    public List<OnlineComplaint> searchOnlineComplaintsByTitle(@RequestParam("query") String query) {
        // Perform the search operation in the database based on the query
        List<OnlineComplaint> searchResults = onlineComplaintService.searchOnlineComplaintsByTitle(query);

        // Iterate through the search results and apply the desired formatting
        for (OnlineComplaint complaint : searchResults) {
            String ocName = complaint.getOcName();
            if (ocName != null && ocName.length() > 1) {
                String firstLetter = ocName.substring(0, 1);
                String maskedName = firstLetter + "**";
                complaint.setOcName(maskedName);
            }

            // Update and retrieve the OnlineComplaint with the formatted ocDateFormatted
            OnlineComplaint updatedComplaint = onlineComplaintService.updateAndRetrieveOnlineComplaint(complaint);
            complaint.setOcDateFormatted(updatedComplaint.getOcDateFormatted());
        }

        return searchResults;
    }

}