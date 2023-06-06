package com.sos.signal.onlinecomplaint;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
        return "redirect:/online_complaint_form/submit-success";
    }

    @RequestMapping(value = "/online_complaint_form/submit-success", method = RequestMethod.GET)
    public String showSuccessPage() {
        return "online_complaint/online_complaint_form_submit_success";
    }

//////////////////////////////////////////////////////////////////////
//    Without Pagination
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

//////////////////////////////////////////////////////////////////////
//    Pagination
//@RequestMapping(value = "/online-complaint", method = RequestMethod.GET)
//public String showOnlineComplaintList(@RequestParam(defaultValue = "1") int pageNumber, Model model) {
//    int pageSize = 5; // Set the desired page size
//
//    // Adjust the page number to 0-based index
//    int adjustedPageNumber = pageNumber - 1;
//
//    // Fetch the latest results from the service for the specified page
//    List<OnlineComplaint> latestResults = onlineComplaintService.getLatestResults(adjustedPageNumber, pageSize);
//
//    // Add the latest results to the model
//    model.addAttribute("latestResults", latestResults);
//
//    return "online_complaint/online_complaint_list";
//}

//    @RequestMapping(value = "/online-complaint/latest-results", method = RequestMethod.GET)
//    @ResponseBody
//    public List<OnlineComplaint> getLatestResults(@RequestParam(defaultValue = "1") int pageNumber) {
//        int pageSize = 5; // Set the desired page size
//
//        // Adjust the page number to 0-based index
//        int adjustedPageNumber = pageNumber - 1;
//
//        // Fetch the latest results from the service for the specified page
//        List<OnlineComplaint> latestResults = onlineComplaintService.getLatestResults(adjustedPageNumber, pageSize);
//
//        return latestResults;
//    }


    @RequestMapping(value = "/online-complaint/search", method = RequestMethod.GET)
    @ResponseBody
    public List<OnlineComplaint> searchOnlineComplaintsByTitle(@RequestParam("query") String query) {
        return onlineComplaintService.searchOnlineComplaintsByTitle(query);
    }

}