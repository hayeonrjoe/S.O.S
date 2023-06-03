package com.sos.signal.counselingappointment;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class CounselingAppointmentController {

    private final CounselingAppointmentService counselingAppointmentService;

    public CounselingAppointmentController(CounselingAppointmentService counselingAppointmentService) {
        this.counselingAppointmentService = counselingAppointmentService;
    }

    @RequestMapping(value = "/counseling-appointment-form", method = RequestMethod.GET)
    public String showCounselingAppointmentForm(Model model) {
        model.addAttribute("counselingAppointment", new CounselingAppointment());
        return "counseling_appointment/counseling_appointment_form";
    }

    @RequestMapping(value = "/counseling-appointment-form/submit", method = RequestMethod.POST)
    public String submitCounselingAppointmentForm(@ModelAttribute("counselingAppointment") CounselingAppointment counselingAppointment, BindingResult bindingResult) {
        counselingAppointmentService.validateCounselingAppointment(counselingAppointment, bindingResult);

        if (bindingResult.hasErrors()) {
            return "counseling_appointment/counseling_appointment_form";
        }

        counselingAppointmentService.saveCounselingAppointment(counselingAppointment);

        // Redirect to the success page
        return "redirect:/counseling-appointment-form/submit-success";
    }

    @RequestMapping(value = "/counseling-appointment-form/submit-success", method = RequestMethod.GET)
    public String showSuccessPage() {
        return "counseling_appointment/counseling_appointment_form_submit_success";
    }

}
