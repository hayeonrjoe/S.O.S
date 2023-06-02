package com.sos.signal.policecomplaint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/police-complaint-form")
public class PoliceComplaintController {

    @Autowired
    private PoliceComplaintRepository policeComplaintRepository;

    @Autowired
    private ResourceLoader resourceLoader;

    @GetMapping
    public ResponseEntity<Resource> showPoliceComplaintForm() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:templates/police_complaint/police_complaint_form.html");
        if (resource.exists()) {
            return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/submit")
    public ResponseEntity<String> submitPoliceComplaint(@ModelAttribute PoliceComplaint policeComplaint) {

        // Perform validation on the onlineComplaint object and ocAdvisors
        // password
        if (policeComplaint.getPc_pw() == null || policeComplaint.getPc_pw().isEmpty()) {
            return ResponseEntity.badRequest().body("비밀번호가 필요합니다.");
        }
//         title
        if (policeComplaint.getPc_title() == null || policeComplaint.getPc_title().isEmpty()) {
            return ResponseEntity.badRequest().body("제목이 필요합니다.");
        }
        // content
        if (policeComplaint.getPc_content() == null || policeComplaint.getPc_content().isEmpty()) {
            return ResponseEntity.badRequest().body("내용이 필요합니다.");
        }

        // name
        if (policeComplaint.getPc_name() == null || policeComplaint.getPc_name().isEmpty()) {
            return ResponseEntity.badRequest().body("신고자 이름이 필요합니다.");
        }

        policeComplaint.setPc_response_status("답변대기");

        policeComplaint.setPc_date(LocalDateTime.now()); // set oc_date to current date and time

        // Save the complaint using the repository
        policeComplaintRepository.save(policeComplaint);

        return ResponseEntity.ok("신고 접수가 완료되었습니다.");
    }
}