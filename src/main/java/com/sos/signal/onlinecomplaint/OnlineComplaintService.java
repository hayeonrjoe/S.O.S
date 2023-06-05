package com.sos.signal.onlinecomplaint;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Service
public class OnlineComplaintService {

    private final OnlineComplaintRepository onlineComplaintRepository;

    public OnlineComplaintService(OnlineComplaintRepository onlineComplaintRepository) {
        this.onlineComplaintRepository = onlineComplaintRepository;
    }

    public void validateOnlineComplaint(OnlineComplaint onlineComplaint, BindingResult bindingResult) {
        if (onlineComplaint.getOc_pw() == null || onlineComplaint.getOc_pw().isEmpty()) {
            bindingResult.rejectValue("oc_pw", "error.oc_pw", "비밀번호가 필요합니다.");
        }
        if (onlineComplaint.getOc_name() == null || onlineComplaint.getOc_name().isEmpty()) {
            bindingResult.rejectValue("oc_name", "error.oc_name", "이름이 필요합니다.");
        }
        if (onlineComplaint.getOc_advisor() == null || onlineComplaint.getOc_advisor().isEmpty()) {
            bindingResult.rejectValue("oc_advisor", "error.oc_advisor", "도움 받고 싶은 사람을 선택해 주세요.");
        }
        if (onlineComplaint.getOc_title() == null || onlineComplaint.getOc_title().isEmpty()) {
            bindingResult.rejectValue("oc_title", "error.oc_title", "제목이 필요합니다.");
        }
        if (onlineComplaint.getOc_content() == null || onlineComplaint.getOc_content().isEmpty()) {
            bindingResult.rejectValue("oc_content", "error.oc_content", "내용이 필요합니다.");
        }

    }

    public void saveOnlineComplaint(OnlineComplaint onlineComplaint) {
        onlineComplaint.setOc_response_status("답변대기");
        onlineComplaint.setOc_date(LocalDateTime.now());
        onlineComplaintRepository.save(onlineComplaint);
    }
}