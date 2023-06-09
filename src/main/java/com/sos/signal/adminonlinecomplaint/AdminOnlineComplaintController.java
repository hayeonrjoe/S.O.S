package com.sos.signal.adminonlinecomplaint;

import com.sos.signal.onlinecomplaint.OnlineComplaint;
import com.sos.signal.onlinecomplaint.OnlineComplaintService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class AdminOnlineComplaintController {

    private final OnlineComplaintService onlineComplaintService;

    public AdminOnlineComplaintController(OnlineComplaintService onlineComplaintService) {
        this.onlineComplaintService = onlineComplaintService;
    }
//
//    @RequestMapping(value = "/online-complaint-form", method = RequestMethod.GET)
//    public String showOnlineComplaintForm(Model model) {
//        model.addAttribute("onlineComplaint", new OnlineComplaint());
//        return "online_complaint/online_complaint_form";
//    }
//
//    @RequestMapping(value = "/online-complaint-form/submit", method = RequestMethod.POST)
//    public String submitOnlineComplaintForm(@ModelAttribute("onlineComplaint") OnlineComplaint onlineComplaint, BindingResult bindingResult) {
//
//        onlineComplaintService.validateOnlineComplaint(onlineComplaint, bindingResult);
//
//        if (bindingResult.hasErrors()) {
//            return "online_complaint/online_complaint_form";
//        }
//
//        onlineComplaintService.saveOnlineComplaint(onlineComplaint);
//
//        // Redirect to the success page
//        return "redirect:/online_complaint_form/submit-success";
//    }
//
//    @RequestMapping(value = "/online_complaint_form/submit-success", method = RequestMethod.GET)
//    public String showSuccessPage() {
//        return "online_complaint/online_complaint_form_submit_success";
//    }
//

    // Police Admin
    @RequestMapping(value = "/online-complaint/admin/p/check-type", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Boolean> checkPAdminType(@RequestBody Map<String, String> requestBody) {
        int ocId = Integer.parseInt(requestBody.get("ocId"));
        String ocAdvisor = requestBody.get("ocAdvisor");

        // Retrieve the OnlineComplaint object by ocId from the database
        OnlineComplaint onlineComplaint = onlineComplaintService.findById(ocId);

        // Check if the type matches
        boolean valid = (onlineComplaint != null && ocAdvisor.equals(onlineComplaint.getOcAdvisor()));

        // Prepare the response JSON
        Map<String, Boolean> response = new HashMap<>();

        response.put("valid", valid);

        return response;
    }

    @RequestMapping(value = "/online-complaint/admin/p", method = RequestMethod.GET)
    public String showPAdminOnlineComplaintList(Model model) {

        // Fetch the latest results from the service for the specified page
        List<OnlineComplaint> latestResults = onlineComplaintService.getLatestResults();

        // Add the latest results to the model
        model.addAttribute("latestResults", latestResults);

        return "admin/police/admin_online_complaint_list_police";
    }

    @RequestMapping(value = "/online-complaint/admin/p/latest-results", method = RequestMethod.GET)
    @ResponseBody
    public List<OnlineComplaint> getPAdminLatestResults() {

        // Fetch the latest results from the service for the specified page
        return onlineComplaintService.getLatestResults();

    }

    @RequestMapping(value = "/online-complaint/admin/p/search", method = RequestMethod.GET)
    @ResponseBody
    public List<OnlineComplaint> searchPAdminOnlineComplaintsByTitle(@RequestParam("query") String query) {
        return onlineComplaintService.searchOnlineComplaintsByTitle(query);
    }

    @RequestMapping(value = "/online-complaint/admin/p/detail", method = RequestMethod.GET)
    public String getPAdminComplaintDetail(@RequestParam("num") int ocId, Model model) {
        // Retrieve the complaint detail based on ocId
        OnlineComplaint complaint = onlineComplaintService.getComplaintById(ocId);

        if (complaint != null) {
            // Add the complaint object to the model
            model.addAttribute("complaint", complaint);
//            if (complaint.getOcResponseContent() == null) return "admin/police/admin_complaint_comment_form_police";
            // Return the name of the HTML page for the complaint detail
            return "admin/police/admin_online_complaint_detail_police";
        } else {
            // Handle the case when the complaint is not found
            // You can redirect to an error page or return an appropriate response
            return "admin/police/admin_online_complaint_list_police";
        }
    }


    // Nonpolice Admin
    @RequestMapping(value = "/online-complaint/admin/n/check-type", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Boolean> checkNAdminType(@RequestBody Map<String, String> requestBody) {
        int ocId = Integer.parseInt(requestBody.get("ocId"));
        String ocAdvisor = requestBody.get("ocAdvisor");

        // Retrieve the OnlineComplaint object by ocId from the database
        OnlineComplaint onlineComplaint = onlineComplaintService.findById(ocId);

        // Check if the type matches
        boolean valid = (onlineComplaint != null && ocAdvisor.equals(onlineComplaint.getOcAdvisor()));

        // Prepare the response JSON
        Map<String, Boolean> response = new HashMap<>();

        response.put("valid", valid);

        return response;
    }

    @RequestMapping(value = "/online-complaint/admin/n", method = RequestMethod.GET)
    public String showNAdminOnlineComplaintList(Model model) {

        // Fetch the latest results from the service for the specified page
        List<OnlineComplaint> latestResults = onlineComplaintService.getLatestResults();

        // Add the latest results to the model
        model.addAttribute("latestResults", latestResults);

        return "admin/nonpolice/admin_online_complaint_list_nonpolice";
    }

    @RequestMapping(value = "/online-complaint/admin/n/latest-results", method = RequestMethod.GET)
    @ResponseBody
    public List<OnlineComplaint> getNAdminLatestResults() {

        // Fetch the latest results from the service for the specified page
        return onlineComplaintService.getLatestResults();

    }

    @RequestMapping(value = "/online-complaint/admin/n/search", method = RequestMethod.GET)
    @ResponseBody
    public List<OnlineComplaint> searchNAdminOnlineComplaintsByTitle(@RequestParam("query") String query) {
        return onlineComplaintService.searchOnlineComplaintsByTitle(query);
    }

    @RequestMapping(value = "/online-complaint/admin/n/detail", method = RequestMethod.GET)
    public String getNAdminComplaintDetail(@RequestParam("num") int ocId, Model model) {
        // Retrieve the complaint detail based on ocId
        OnlineComplaint complaint = onlineComplaintService.getComplaintById(ocId);

        if (complaint != null) {
            // Add the complaint object to the model
            model.addAttribute("complaint", complaint);
//            if (complaint.getOcResponseContent() == null) return "admin/police/admin_complaint_comment_form_police";
            // Return the name of the HTML page for the complaint detail
            return "admin/nonpolice/admin_online_complaint_detail_nonpolice";
        } else {
            // Handle the case when the complaint is not found
            // You can redirect to an error page or return an appropriate response
            return "admin/nonpolice/admin_online_complaint_list_nonpolice";
        }
    }

}