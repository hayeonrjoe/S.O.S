package com.sos.signal.policecomplaint.service;

import com.sos.signal.policecomplaint.dto.PoliceComplaint;
import com.sos.signal.policecomplaint.repository.PoliceComplaintRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;

@Service
public class PoliceComplaintService {

    private final PoliceComplaintRepository policeComplaintRepository;

    public PoliceComplaintService(PoliceComplaintRepository policeComplaintRepository) {
        this.policeComplaintRepository = policeComplaintRepository;
    }

    public void validatePoliceComplaint(PoliceComplaint policeComplaint, BindingResult bindingResult) {
        if (policeComplaint.getPc_pw() == null || policeComplaint.getPc_pw().isEmpty()) {
            bindingResult.rejectValue("pc_pw", "error.pc_pw", "비밀번호가 필요합니다.");
        }
        if (policeComplaint.getPc_title() == null || policeComplaint.getPc_title().isEmpty()) {
            bindingResult.rejectValue("pc_title", "error.pc_title", "제목이 필요합니다.");
        }
        if (policeComplaint.getPc_content() == null || policeComplaint.getPc_content().isEmpty()) {
            bindingResult.rejectValue("pc_content", "error.pc_content", "내용이 필요합니다.");
        }
        if (policeComplaint.getPc_name() == null || policeComplaint.getPc_name().isEmpty()) {
            bindingResult.rejectValue("pc_name", "error.pc_name", "신고자 이름이 필요합니다.");
        }

    }

    public void savePoliceComplaint(PoliceComplaint policeComplaint) {
        policeComplaint.setPc_response_status("답변대기");
        policeComplaint.setPc_date(LocalDateTime.now());
        policeComplaintRepository.save(policeComplaint);
    }
}
