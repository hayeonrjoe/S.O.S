package com.sos.signal.policecomplaint.service;

import com.sos.signal.policecomplaint.dto.AttachmentFile;
import com.sos.signal.policecomplaint.dto.PoliceComplaint;
import com.sos.signal.policecomplaint.repository.PoliceComplaintRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class PoliceComplaintService {

    private final AttachmentFileService attachmentFileService;
    private final PoliceComplaintRepository policeComplaintRepository;

    public PoliceComplaintService(AttachmentFileService attachmentFileService, PoliceComplaintRepository policeComplaintRepository) {
        this.attachmentFileService = attachmentFileService;
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

    public void savePoliceComplaint(PoliceComplaint policeComplaint, MultipartFile[] files) throws IOException {
        // Save attachment files and retrieve the fileIds
        String fileIds = attachmentFileService.saveAttachmentFiles(files);

        if (!fileIds.isEmpty()) {
            policeComplaint.setPc_file_ids(fileIds);
        }

        // Set other properties and save police complaint
        policeComplaint.setPc_response_status("답변대기");
        policeComplaint.setPc_date(LocalDateTime.now());
        policeComplaintRepository.save(policeComplaint);
    }
}
