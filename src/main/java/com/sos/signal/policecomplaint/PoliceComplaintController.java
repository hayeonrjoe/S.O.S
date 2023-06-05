package com.sos.signal.policecomplaint;

import com.sos.signal.policecomplaint.dto.PoliceComplaint;
import com.sos.signal.policecomplaint.service.PoliceComplaintService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class PoliceComplaintController {

    private final PoliceComplaintService policeComplaintService;

    public PoliceComplaintController(PoliceComplaintService policeComplaintService) {
        this.policeComplaintService = policeComplaintService;
    }

    @RequestMapping(value = "/police-complaint-form", method = RequestMethod.GET)
    public String showPoliceComplaintForm(Model model) {
        model.addAttribute("policeComplaint", new PoliceComplaint());
        return "police_complaint/police_complaint_form";
    }

    @RequestMapping(value = "/police-complaint-form/submit", method = RequestMethod.POST)
    public String submitPoliceComplaintForm(@ModelAttribute("policeComplaint") PoliceComplaint policeComplaint,
                                            BindingResult bindingResult, @RequestParam("files") MultipartFile[] files) {
        policeComplaintService.validatePoliceComplaint(policeComplaint, bindingResult);

        if (bindingResult.hasErrors()) {
            return "police_complaint/police_complaint_form";
        }

        try {
            policeComplaintService.savePoliceComplaint(policeComplaint, files);
        } catch (IOException e) {
            // Handle the exception appropriately
            return "police_complaint/police_complaint_form";
        }

        // Redirect to the success page
        return "redirect:/police-complaint-form/submit-success";
    }

    @RequestMapping(value = "/police-complaint-form/submit-success", method = RequestMethod.GET)
    public String showSuccessPage() {
        return "police_complaint/police_complaint_form_submit_success";
    }
}
