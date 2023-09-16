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

    @GetMapping("/counseling-appointment-form")
    public String showCounselingAppointmentForm(Model model) {
        model.addAttribute("counselingAppointment", new CounselingAppointment());
        return "counseling_appointment/counseling_appointment_form";
    }

    @PostMapping("/counseling-appointment-form/submit")
    public String submitCounselingAppointmentForm(@ModelAttribute("counselingAppointment") CounselingAppointment counselingAppointment, BindingResult bindingResult) {
        counselingAppointmentService.validateCounselingAppointment(counselingAppointment, bindingResult);

        if (bindingResult.hasErrors()) {
            return "counseling_appointment/counseling_appointment_form";
        }

        counselingAppointmentService.saveCounselingAppointment(counselingAppointment);

        return "redirect:/counseling-appointment-form/submit-success";
    }

    @GetMapping(value = "/counseling-appointment-form/submit-success")
    public String showSuccessPage() {
        return "counseling_appointment/counseling_appointment_form_submit_success";
    }

}
