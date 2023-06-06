package com.sos.signal.counselingsession;

import com.sos.signal.counselingappointment.CounselingAppointment;
import com.sos.signal.counselingappointment.CounselingAppointmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CounselingSessionController {

    @RequestMapping(value = "/counseling-session", method = RequestMethod.GET)
    public String showCounselingSession() {
        return "counseling_session";
    }

}
