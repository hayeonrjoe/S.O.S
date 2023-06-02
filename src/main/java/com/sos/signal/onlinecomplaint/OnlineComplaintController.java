package com.sos.signal.onlinecomplaint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/online-complaint-form")
public class OnlineComplaintController {

    @Autowired
    private OnlineComplaintRepository onlineComplaintRepository;

    @Autowired
    private ResourceLoader resourceLoader;

    @GetMapping
    public ResponseEntity<Resource> showOnlineComplaintForm() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:templates/online_complaint/online_complaint_form.html");
        if (resource.exists()) {
            return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/submit")
    public ResponseEntity<String> submitOnlineComplaint(@ModelAttribute OnlineComplaint onlineComplaint, @RequestParam("oc_advisor") String oc_advisor) {

        // Perform validation on the onlineComplaint object and ocAdvisors
        // password
        if (onlineComplaint.getOc_pw() == null || onlineComplaint.getOc_pw().isEmpty()) {
            return ResponseEntity.badRequest().body("비밀번호가 필요합니다.");
        }
        // name
        if (onlineComplaint.getOc_name() == null || onlineComplaint.getOc_name().isEmpty()) {
            return ResponseEntity.badRequest().body("이름이 필요합니다.");
        }

        // advisor
        if (oc_advisor == null || oc_advisor.isEmpty()) {
            return ResponseEntity.badRequest().body("도움 받고 싶은 사람을 선택해 주세요.");
        }

        // title
        if (onlineComplaint.getOc_title() == null || onlineComplaint.getOc_title().isEmpty()) {
            return ResponseEntity.badRequest().body("제목이 필요합니다.");
        }
        // content
        if (onlineComplaint.getOc_content() == null || onlineComplaint.getOc_content().isEmpty()) {
            return ResponseEntity.badRequest().body("내용이 필요합니다.");
        }

        onlineComplaint.setOc_response_status("답변대기");

        // Set the selected advisor options in the onlineComplaint object
        onlineComplaint.setOc_advisor(oc_advisor);

        onlineComplaint.setOc_date(LocalDateTime.now()); // set oc_date to current date and time

        // Save the complaint using the repository
        onlineComplaintRepository.save(onlineComplaint);

        return ResponseEntity.ok("비공개 온라인 상담 신청이 완료되었습니다.");
    }
}