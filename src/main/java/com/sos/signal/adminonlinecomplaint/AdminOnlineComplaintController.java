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
    @GetMapping("/online-complaint/admin/p")
    public String showPAdminOnlineComplaintList(Model model) {

        List<OnlineComplaint> allResults = onlineComplaintService.getLatestResults();

        List<OnlineComplaint> filteredResults = allResults.stream()
                .filter(oc -> "경찰".equals(oc.getOcAdvisor()) && "답변대기".equals(oc.getOcResponseStatus()))
                .collect(Collectors.toList());

        model.addAttribute("latestResults", filteredResults);

        return "admin/police/online_complaint/admin_online_complaint_list_police";
    }

    @GetMapping("/online-complaint/admin/p/latest-results")
    @ResponseBody
    public List<OnlineComplaint> getPAdminLatestResults() {

        List<OnlineComplaint> allResults = onlineComplaintService.getLatestResults();

        List<OnlineComplaint> filteredResults = allResults.stream()
                .filter(oc -> "경찰".equals(oc.getOcAdvisor()) && "답변대기".equals(oc.getOcResponseStatus()))
                .collect(Collectors.toList());

        return filteredResults;
    }

    @GetMapping("/online-complaint/admin/p/search")
    @ResponseBody
    public List<OnlineComplaint> searchPAdminOnlineComplaintsByTitle(@RequestParam("query") String query) {
        List<OnlineComplaint> complaints = onlineComplaintService.searchOnlineComplaintsByTitle(query);
        List<OnlineComplaint> filteredComplaints = new ArrayList<>();

        for (OnlineComplaint complaint : complaints) {
            if ("경찰".equals(complaint.getOcAdvisor()) && "답변대기".equals(complaint.getOcResponseStatus())) {
                filteredComplaints.add(complaint);
            }
        }

        return filteredComplaints;
    }

    @GetMapping("/online-complaint-comment-form/admin/p")
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

    @PostMapping("/online-complaint-comment-form/admin/p/submit")
    public String submitPAdminOnlineComplaintCommentForm(
            @RequestParam("aEmail") String aEmail,
            @RequestParam("aPw") String aPw,
            @RequestParam("ocResponseContent") String ocResponseContent,
            @RequestParam("ocId") Integer ocId,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession();
        Integer aId = (Integer) session.getAttribute("aId");


        if (aId == null) {
            aId = adminService.getAdminIdByEmail(aEmail);

            if (aId == null) {
                return "redirect:/online-complaint-comment-form/admin/p";
            }
        }

        boolean passwordMatch = adminService.verifyAdminPassword(aEmail, aPw);

        if (!passwordMatch) {
            return "redirect:/online-complaint-comment-form/admin/p";
        }

        onlineComplaintService.updateAdminOnlineComplaint(aId, ocId, ocResponseContent);

        return "redirect:/online-complaint-comment-form/admin/p/submit-success";
    }

    @GetMapping("/online-complaint-comment-form/admin/p/submit-success")
    public String showPAdminSuccessPage() {
        return "admin/police/online_complaint/admin_online_complaint_form_police_submit_success";
    }

    @GetMapping("/online-complaint/admin/p/detail")
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
    // Counselor Admin
    @GetMapping("/online-complaint/admin/c")
    public String showCAdminOnlineComplaintList(Model model) {

        List<OnlineComplaint> allResults = onlineComplaintService.getLatestResults();

        List<OnlineComplaint> filteredResults = allResults.stream()
                .filter(oc -> "상담사".equals(oc.getOcAdvisor()) && "답변대기".equals(oc.getOcResponseStatus()))
                .collect(Collectors.toList());

        model.addAttribute("latestResults", filteredResults);

        return "admin/counselor/online_complaint/admin_online_complaint_list_counselor";
    }

    @GetMapping("/online-complaint/admin/c/latest-results")
    @ResponseBody
    public List<OnlineComplaint> getCAdminLatestResults() {

        List<OnlineComplaint> allResults = onlineComplaintService.getLatestResults();

        List<OnlineComplaint> filteredResults = allResults.stream()
                .filter(oc -> "상담사".equals(oc.getOcAdvisor()) && "답변대기".equals(oc.getOcResponseStatus()))
                .collect(Collectors.toList());

        return filteredResults;
    }

    @GetMapping("/online-complaint/admin/c/search")
    @ResponseBody
    public List<OnlineComplaint> searchCAdminOnlineComplaintsByTitle(@RequestParam("query") String query) {
        List<OnlineComplaint> complaints = onlineComplaintService.searchOnlineComplaintsByTitle(query);
        List<OnlineComplaint> filteredComplaints = new ArrayList<>();

        for (OnlineComplaint complaint : complaints) {
            if ("상담사".equals(complaint.getOcAdvisor()) && "답변대기".equals(complaint.getOcResponseStatus())) {
                filteredComplaints.add(complaint);
            }
        }

        return filteredComplaints;
    }

    @GetMapping("/online-complaint-comment-form/admin/c")
    public String showCAdminOnlineComplaintForm(@RequestParam("num") String num, Model model) {
        int ocId = Integer.parseInt(num);
        OnlineComplaint complaint = onlineComplaintService.getComplaintById(ocId);

        if (complaint != null) {
            model.addAttribute("complaint", complaint);

            return "admin/counselor/online_complaint/admin_online_complaint_comment_form_counselor";
        } else {
            return "admin/counselor/online_complaint/admin_online_complaint_list_counselor";
        }
    }

    @PostMapping("/online-complaint-comment-form/admin/c/submit")
    public String submitCAdminOnlineComplaintCommentForm(
            @RequestParam("aEmail") String aEmail,
            @RequestParam("aPw") String aPw,
            @RequestParam("ocResponseContent") String ocResponseContent,
            @RequestParam("ocId") Integer ocId,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession();
        Integer aId = (Integer) session.getAttribute("aId");

        if (aId == null) {
            aId = adminService.getAdminIdByEmail(aEmail);

            if (aId == null) {
                return "redirect:/online-complaint-comment-form/admin/p";
            }
        }

        boolean passwordMatch = adminService.verifyAdminPassword(aEmail, aPw);

        if (!passwordMatch) {
            return "redirect:/online-complaint-comment-form/admin/c";
        }

        onlineComplaintService.updateAdminOnlineComplaint(aId, ocId, ocResponseContent);

        return "redirect:/online-complaint-comment-form/admin/c/submit-success";
    }


    @GetMapping("/online-complaint-comment-form/admin/c/submit-success")
    public String showCAdminSuccessPage() {
        return "admin/counselor/online_complaint/admin_online_complaint_form_counselor_submit_success";
    }

    @GetMapping("/online-complaint/admin/c/detail")
    public String getCAdminComplaintDetail(@RequestParam("num") String num, Model model) {
        int ocId = Integer.parseInt(num);
        OnlineComplaint complaint = onlineComplaintService.getComplaintById(ocId);

        if (complaint != null) {
            model.addAttribute("complaint", complaint);
            if (complaint.getOcResponseContent().equals("답변대기")) {
                return "admin/counselor/online_complaint/admin_online_complaint_comment_form_counselor";
            } else {
                return "admin/counselor/online_complaint/admin_online_complaint_detail_counselor";
            }
        } else {
            return "admin/counselor/online_complaint/admin_online_complaint_list_counselor";
        }
    }

    // Lawyer Admin
    @GetMapping("/online-complaint/admin/l")
    public String showLAdminOnlineComplaintList(Model model) {

        List<OnlineComplaint> allResults = onlineComplaintService.getLatestResults();

        List<OnlineComplaint> filteredResults = allResults.stream()
                .filter(oc -> "변호사".equals(oc.getOcAdvisor()) && "답변대기".equals(oc.getOcResponseStatus()))
                .collect(Collectors.toList());

        model.addAttribute("latestResults", filteredResults);

        return "admin/lawyer/online_complaint/admin_online_complaint_list_lawyer";
    }

    @GetMapping("/online-complaint/admin/l/latest-results")
    @ResponseBody
    public List<OnlineComplaint> getLAdminLatestResults() {

        List<OnlineComplaint> allResults = onlineComplaintService.getLatestResults();

        List<OnlineComplaint> filteredResults = allResults.stream()
                .filter(oc -> "변호사".equals(oc.getOcAdvisor()) && "답변대기".equals(oc.getOcResponseStatus()))
                .collect(Collectors.toList());

        return filteredResults;
    }

    @GetMapping("/online-complaint/admin/l/search")
    @ResponseBody
    public List<OnlineComplaint> searchLAdminOnlineComplaintsByTitle(@RequestParam("query") String query) {
        List<OnlineComplaint> complaints = onlineComplaintService.searchOnlineComplaintsByTitle(query);
        List<OnlineComplaint> filteredComplaints = new ArrayList<>();

        for (OnlineComplaint complaint : complaints) {
            if ("변호사".equals(complaint.getOcAdvisor()) && "답변대기".equals(complaint.getOcResponseStatus())) {
                filteredComplaints.add(complaint);
            }
        }

        return filteredComplaints;
    }

    @GetMapping("/online-complaint-comment-form/admin/l")
    public String showLAdminOnlineComplaintForm(@RequestParam("num") String num, Model model) {
        int ocId = Integer.parseInt(num);
        OnlineComplaint complaint = onlineComplaintService.getComplaintById(ocId);

        if (complaint != null) {
            model.addAttribute("complaint", complaint);

            return "admin/lawyer/online_complaint/admin_online_complaint_comment_form_lawyer";
        } else {
            return "admin/lawyer/online_complaint/admin_online_complaint_list_lawyer";
        }
    }

    @PostMapping("/online-complaint-comment-form/admin/l/submit")
    public String submitLAdminOnlineComplaintCommentForm(
            @RequestParam("aEmail") String aEmail,
            @RequestParam("aPw") String aPw,
            @RequestParam("ocResponseContent") String ocResponseContent,
            @RequestParam("ocId") Integer ocId,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession();
        Integer aId = (Integer) session.getAttribute("aId");

        if (aId == null) {
            aId = adminService.getAdminIdByEmail(aEmail);

            if (aId == null) {
                return "redirect:/online-complaint-comment-form/admin/p";
            }
        }

        boolean passwordMatch = adminService.verifyAdminPassword(aEmail, aPw);

        if (!passwordMatch) {
            return "redirect:/online-complaint-comment-form/admin/l";
        }

        onlineComplaintService.updateAdminOnlineComplaint(aId, ocId, ocResponseContent);

        return "redirect:/online-complaint-comment-form/admin/l/submit-success";
    }


    @GetMapping("/online-complaint-comment-form/admin/l/submit-success")
    public String showLAdminSuccessPage() {
        return "admin/lawyer/online_complaint/admin_online_complaint_form_lawyer_submit_success";
    }

    @GetMapping("/online-complaint/admin/l/detail")
    public String getLAdminComplaintDetail(@RequestParam("num") String num, Model model) {
        int ocId = Integer.parseInt(num);
        OnlineComplaint complaint = onlineComplaintService.getComplaintById(ocId);

        if (complaint != null) {
            model.addAttribute("complaint", complaint);
            if (complaint.getOcResponseContent().equals("답변대기")) {
                return "admin/lawyer/online_complaint/admin_online_complaint_comment_form_lawyer";
            } else {
                return "admin/lawyer/online_complaint/admin_online_complaint_detail_lawyer";
            }
        } else {
            return "admin/lawyer/online_complaint/admin_online_complaint_list_lawyer";
        }
    }
}