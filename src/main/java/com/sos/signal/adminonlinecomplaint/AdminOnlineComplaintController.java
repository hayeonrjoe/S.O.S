package com.sos.signal.adminonlinecomplaint;

import com.sos.signal.admin.AdminService;
import com.sos.signal.onlinecomplaint.OnlineComplaint;
import com.sos.signal.onlinecomplaint.OnlineComplaintService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.sos.signal.onlinecomplaint.SearchResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AdminOnlineComplaintController {

    private final AdminService adminService;
    private final OnlineComplaintService onlineComplaintService;

    public AdminOnlineComplaintController(AdminService adminService, OnlineComplaintService onlineComplaintService) {
        this.adminService = adminService;
        this.onlineComplaintService = onlineComplaintService;
    }

    // Police Admin
    @RequestMapping(value = "/online-complaint/admin/p", method = RequestMethod.GET)
    public String showPAdminOnlineComplaintList(Model model) {

        List<OnlineComplaint> allResults = onlineComplaintService.getLatestResults();

        List<OnlineComplaint> filteredResults = allResults.stream()
                .filter(oc -> oc.getOcAdvisor().equals("경찰"))
                .collect(Collectors.toList());

        model.addAttribute("latestResults", filteredResults);

        return "admin/police/online_complaint/admin_online_complaint_list_police";
    }

    @RequestMapping(value = "/online-complaint/admin/p/latest-results", method = RequestMethod.GET)
    @ResponseBody
    public List<OnlineComplaint> getPAdminLatestResults() {

        List<OnlineComplaint> allResults = onlineComplaintService.getLatestResults();

        List<OnlineComplaint> filteredResults = allResults.stream()
                .filter(oc -> oc.getOcAdvisor().equals("경찰"))
                .collect(Collectors.toList());

        return filteredResults;
    }

    @RequestMapping(value = "/online-complaint/admin/p/search", method = RequestMethod.GET)
    @ResponseBody
    public List<OnlineComplaint> searchPAdminOnlineComplaintsByTitle(@RequestParam("query") String query) {
        List<OnlineComplaint> complaints = onlineComplaintService.searchOnlineComplaintsByTitle(query);
        List<OnlineComplaint> filteredComplaints = new ArrayList<>();

        for (OnlineComplaint complaint : complaints) {
            if ("경찰".equals(complaint.getOcAdvisor())) {
                filteredComplaints.add(complaint);
            }
        }

        return filteredComplaints;
    }

    @RequestMapping(value = "/online-complaint-comment-form/admin/p", method = RequestMethod.GET)
    public String showPAdminOnlineComplaintForm(@RequestParam("num") String num, Model model) {
        int ocId = Integer.parseInt(num);
        OnlineComplaint complaint = onlineComplaintService.getComplaintById(ocId);

        if (complaint != null) {
            model.addAttribute("complaint", complaint);

            return "admin/police/online_complaint/admin_online_complaint_comment_form_police";
        } else {
            return "admin/police/online_complaint/admin_online_complaint_list_police";
        }
    }

    @RequestMapping(value = "/online-complaint-comment-form/admin/p/submit", method = RequestMethod.POST)
    public String submitPAdminOnlineComplaintCommentForm(
            @RequestParam("a_pw") String aPw,
            @RequestParam("ocResponseContent") String ocResponseContent,
            @RequestParam("ocId") Integer ocId,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession();
        Integer aId = (Integer) session.getAttribute("aId");

        boolean passwordMatch = adminService.verifyAdminPassword(aId, aPw);

        if (!passwordMatch) {
            return "redirect:/online-complaint-comment-form/admin/p";
        }

        onlineComplaintService.updatePAdminOnlineComplaint(aId, ocId, ocResponseContent);

        return "redirect:/online-complaint-comment-form/admin/p/submit-success";
    }


    @RequestMapping(value = "/online-complaint-comment-form/admin/p/submit-success", method = RequestMethod.GET)
    public String showPAdminSuccessPage() {
        return "admin/police/online_complaint/admin_online_complaint_form_police_submit_success";
    }

    @RequestMapping(value = "/online-complaint/admin/p/detail", method = RequestMethod.GET)
    public String getPAdminComplaintDetail(@RequestParam("num") String num, Model model) {
       int ocId = Integer.parseInt(num);
        OnlineComplaint complaint = onlineComplaintService.getComplaintById(ocId);

        if (complaint != null) {
            model.addAttribute("complaint", complaint);
            if (complaint.getOcResponseContent().equals("답변대기")) {
                return "admin/police/online_complaint/admin_online_complaint_comment_form_police";
            } else {
                return "admin/police/online_complaint/admin_online_complaint_detail_police";
            }
       } else {
            return "admin/police/online_complaint/admin_online_complaint_list_police";
       }
    }

    // Nonpolice Admin
    @RequestMapping(value = "/online-complaint/admin/n", method = RequestMethod.GET)
    public String showNAdminOnlineComplaintList(Model model) {

        // Fetch the latest results from the service for the specified page
        List<OnlineComplaint> latestResults = onlineComplaintService.getLatestResults();

        // Add the latest results to the model
        model.addAttribute("latestResults", latestResults);

        return "admin/nonpolice/online_complaint/admin_online_complaint_list_nonpolice";
    }

    @RequestMapping(value = "/online-complaint/admin/n/latest-results", method = RequestMethod.GET)
    @ResponseBody
    public List<OnlineComplaint> getNAdminLatestResults() {

        // Fetch the latest results from the service for the specified page
        return onlineComplaintService.getLatestResults();

    }

    @GetMapping("/online-complaint/admin/n/search")
    public List<SearchResult> searchNAdminOnlineComplaintsByTitle(@RequestParam String query, @RequestParam Integer aId) {
        // Fetch the adminType based on aId
        String adminType = adminService.getAdminType(aId);

        // Perform the search operation using the query, adminType, and ocAdvisor values
        List<OnlineComplaint> onlineComplaints = onlineComplaintService.searchByTitleAndAdminType(query, adminType);

        // Map the OnlineComplaint objects to SearchResult objects
        return mapToSearchResults(onlineComplaints);
    }

    private List<SearchResult> mapToSearchResults(List<OnlineComplaint> onlineComplaints) {
        return onlineComplaints.stream()
                .map(onlineComplaint -> {
                    SearchResult searchResult = new SearchResult();
                    searchResult.setOcId(onlineComplaint.getOcId());
                    searchResult.setOcTitle(onlineComplaint.getOcTitle());
                    // Set other fields as needed
                    return searchResult;
                })
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/online-complaint/admin/n/detail", method = RequestMethod.GET)
    public String getNAdminComplaintDetail(@RequestParam("num") int ocId, Model model) {
        // Retrieve the complaint detail based on ocId
        OnlineComplaint complaint = onlineComplaintService.getComplaintById(ocId);

        if (complaint != null) {
            // Add the complaint object to the model
            model.addAttribute("complaint", complaint);
            if (complaint.getOcResponseContent().equals("답변대기")) {
                return "admin/nonpolice/online_complaint/admin_online_complaint_comment_form_nonpolice";
            } else {
                // Return the name of the HTML page for the complaint detail
                return "admin/nonpolice/online_complaint/admin_online_complaint_detail_nonpolice";
            }
        } else {
            // Handle the case when the complaint is not found
            // You can redirect to an error page or return an appropriate response
            return "admin/nonpolice/online_complaint/admin_online_complaint_list_nonpolice";
        }
    }

    @RequestMapping(value = "/online-complaint-comment-form/admin/n", method = RequestMethod.GET)
    public String showNAdminOnlineComplaintForm(Model model) {
        model.addAttribute("onlineComplaint", new OnlineComplaint());
        return "admin/nonpolice/online_complaint/admin_online_complaint_comment_form_nonpolice";
    }

    @RequestMapping(value = "/online-complaint-comment-form/admin/n/submit", method = RequestMethod.POST)
    public String submitNAdminOnlineComplaintCommentForm(
            @RequestParam("a_pw") String aPw,
            @RequestParam("ocResponseContent") String ocResponseContent,
            @RequestParam("ocId") Integer ocId,
            HttpServletRequest request
    ) {
        // Retrieve aId from the session
        HttpSession session = request.getSession();
        Integer aId = (Integer) session.getAttribute("aId");

        // Verify the password using the service method
        boolean passwordMatch = adminService.verifyAdminPassword(aId, aPw);

        if (!passwordMatch) {
            return "redirect:/online-complaint-comment-form/admin/p";
        }

        // Passwords match, proceed with the submission
        // Pass aId, ocId, and ocResponseStatus to the online complaint service and save
        onlineComplaintService.updatePAdminOnlineComplaint(aId, ocId, ocResponseContent);

        // Redirect to the success page
        return "redirect:/online-complaint-comment-form/admin/n/submit-success";
    }


    @RequestMapping(value = "/online-complaint-comment-form/admin/n/submit-success", method = RequestMethod.GET)
    public String shownAdminSuccessPage() {
        return "admin/nonpolice/online_complaint/admin_online_complaint_form_nonpolice_submit_success";
    }


}