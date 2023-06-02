package com.sos.signal.policecomplaint;

import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/police-complaint-form")
public class PoliceComplaintController {

    @Autowired
    private PoliceComplaintRepository policeComplaintRepository;

    @Autowired
    private AttachmentFileRepository attachmentFileRepository;

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
    public ResponseEntity<String> submitPoliceComplaint(@ModelAttribute PoliceComplaint policeComplaint, @RequestPart(value = "file", required = false) MultipartFile[] files) {
        try {
            if (files != null) {
                String fileids = "";
                for (MultipartFile file : files) {
                    if (!file.isEmpty()) {
                        AttachmentFile afile = new AttachmentFile();
                        afile.setAf_file_name(file.getOriginalFilename());
                        afile.setAf_upload_path("c:/sos/upload/");
                        attachmentFileRepository.save(afile);
                        File dfile = new File(afile.getAf_upload_path() + afile.getPc_file_id());
                        file.transferTo(dfile);
                        fileids += afile.getPc_file_id()+"-";
                    }
                }
                policeComplaint.setPc_file_ids(fileids);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

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