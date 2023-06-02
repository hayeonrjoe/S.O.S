package com.sos.signal.counselingappointment;

import com.sos.signal.onlinecomplaint.OnlineComplaint;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/counseling-appointment-form")
public class CounselingAppointmentController {

    @Autowired
    private CounselingAppointmentRepository counselingAppointmentRepository;

    @Autowired
    private ResourceLoader resourceLoader;

    @GetMapping
    public ResponseEntity<Resource> showCounselingAppointmentForm() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:templates/counseling_appointment_form.html");
        if (resource.exists()) {
            return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/submit")
    public ResponseEntity<String> submitCounselingAppointmentForm(@ModelAttribute CounselingAppointment counselingAppointment) {
        // Perform validation on the onlineComplaint object and ocAdvisors
        // name
        if (counselingAppointment.getCa_name() == null || counselingAppointment.getCa_name().isEmpty()) {
            return ResponseEntity.badRequest().body("이름이 필요합니다.");
        }
        // contact
        if (counselingAppointment.getCa_contact() == null || counselingAppointment.getCa_contact().isEmpty()) {
            return ResponseEntity.badRequest().body("연락처가 필요합니다.");
        }
        // area
        if (counselingAppointment.getCa_area() == null || counselingAppointment.getCa_area().isEmpty()) {
            return ResponseEntity.badRequest().body("지역을 선택해주세요.");
        }
        // place
        if (counselingAppointment.getCa_place() == null || counselingAppointment.getCa_place().isEmpty()) {
            return ResponseEntity.badRequest().body("장소를 선택해주세요.");
        }
        // scheduled date
        if (counselingAppointment.getCa_scheduled_date() == null) {
            return ResponseEntity.badRequest().body("희망하는 날짜와 시간을 선택해주세요.");
        }

        counselingAppointment.setCa_response_status("답변대기");

        counselingAppointment.setCa_date(LocalDateTime.now()); // set ca_date to current date and time

        // Save the appointment using the repository
        counselingAppointmentRepository.save(counselingAppointment);

        return ResponseEntity.ok("방문 상담 신청이 완료되었습니다.");
    }
}
