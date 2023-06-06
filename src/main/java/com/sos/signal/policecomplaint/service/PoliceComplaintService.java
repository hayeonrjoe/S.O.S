package com.sos.signal.policecomplaint.service;

import com.sos.signal.onlinecomplaint.OnlineComplaint;
import com.sos.signal.policecomplaint.dto.AttachmentFile;
import com.sos.signal.policecomplaint.dto.PoliceComplaint;
import com.sos.signal.policecomplaint.repository.PoliceComplaintRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PoliceComplaintService {

    private final AttachmentFileService attachmentFileService;
    private final PoliceComplaintRepository policeComplaintRepository;

    public PoliceComplaintService(AttachmentFileService attachmentFileService, PoliceComplaintRepository policeComplaintRepository) {
        this.attachmentFileService = attachmentFileService;
        this.policeComplaintRepository = policeComplaintRepository;
    }

    public void validatePoliceComplaint(PoliceComplaint policeComplaint, BindingResult bindingResult) {
        if (policeComplaint.getPcPw() == null || policeComplaint.getPcPw().isEmpty()) {
            bindingResult.rejectValue("pcPw", "error.pcPw", "비밀번호가 필요합니다.");
        }
        if (policeComplaint.getPcTitle() == null || policeComplaint.getPcTitle().isEmpty()) {
            bindingResult.rejectValue("pcTitle", "error.pcTitle", "제목이 필요합니다.");
        }
        if (policeComplaint.getPcContent() == null || policeComplaint.getPcContent().isEmpty()) {
            bindingResult.rejectValue("pcContent", "error.pcContent", "내용이 필요합니다.");
        }
        if (policeComplaint.getPcName() == null || policeComplaint.getPcName().isEmpty()) {
            bindingResult.rejectValue("pcName", "error.pcName", "신고자 이름이 필요합니다.");
        }

    }

    public void savePoliceComplaint(PoliceComplaint policeComplaint, MultipartFile[] files) throws IOException {
        // Save attachment files and retrieve the fileIds
        String fileIds = attachmentFileService.saveAttachmentFiles(files);

        if (!fileIds.isEmpty()) {
            policeComplaint.setPc_file_ids(fileIds);
        }

        // Set other properties and save police complaint
        policeComplaint.setPcResponseStatus("답변대기");
        LocalDateTime currentDateTime = LocalDateTime.now();
        policeComplaint.setPcDate(currentDateTime);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDateTime.format(dateFormatter);
        policeComplaint.setPcDateFormatted(formattedDate);

        policeComplaintRepository.save(policeComplaint);
    }

    public List<PoliceComplaint> searchPoliceComplaintsByTitle(String query) {
        List<PoliceComplaint> complaints = policeComplaintRepository.findByPcTitleContainingIgnoreCase(query);
        for (PoliceComplaint complaint : complaints) {
            if (complaint.getPcDateFormatted() == null) {
                LocalDateTime pcDateTime = complaint.getPcDate();
                LocalDate pcDate = pcDateTime.toLocalDate();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String formattedDate = pcDate.format(formatter);
                complaint.setPcDateFormatted(formattedDate);
                policeComplaintRepository.save(complaint);
            }
        }
        return complaints;
    }

    public List<PoliceComplaint> getLatestResults() {
        return policeComplaintRepository.findLatestResults();
    }
}
