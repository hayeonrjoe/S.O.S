package com.sos.signal.onlinecomplaint;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OnlineComplaintService {

    private final OnlineComplaintRepository onlineComplaintRepository;

    public OnlineComplaintService(OnlineComplaintRepository onlineComplaintRepository) {
        this.onlineComplaintRepository = onlineComplaintRepository;
    }

    public List<OnlineComplaint> searchOnlineComplaintsByTitle(String query) {
        List<OnlineComplaint> complaints = onlineComplaintRepository.findByOcTitleContainingIgnoreCase(query);
        for (OnlineComplaint complaint : complaints) {
            if (complaint.getOcDateFormatted() == null) {
                LocalDateTime ocDateTime = complaint.getOcDate();
                LocalDate ocDate = ocDateTime.toLocalDate();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String formattedDate = ocDate.format(formatter);
                complaint.setOcDateFormatted(formattedDate);
                onlineComplaintRepository.save(complaint);
            }
        }
        return complaints;
    }

    public OnlineComplaint getComplaintById(int ocId) {
        return onlineComplaintRepository.findByOcId(ocId);
    }

    public OnlineComplaint findByOcId(int ocId) {
        Optional<OnlineComplaint> result = Optional.ofNullable(onlineComplaintRepository.findByOcId(ocId).orElse(null));
        return result.isPresent() ? result.get() : createErrorMessage();
    }

    private OnlineComplaint createErrorMessage() {
        return new OnlineComplaint("페이지를 찾을 수 없습니다. 다시 한번 확인해 주시기 바랍니다.");
    }

    public List<OnlineComplaint> searchByTitleAndAdminType(String query, String adminType) {
        if (adminType.equals("상담사")) {
            return onlineComplaintRepository.findByOcTitleAndOcAdvisor(query, "상담사");
        } else if (adminType.equals("변호사")) {
            return onlineComplaintRepository.findByOcTitleAndOcAdvisor(query, "변호사");
        } else {
            return Collections.emptyList();
        }
    }

    public List<OnlineComplaint> getLatestResults() {
        return onlineComplaintRepository.findLatestResults();
    }

    public void validateOnlineComplaint(OnlineComplaint onlineComplaint, BindingResult bindingResult) {
        if (onlineComplaint.getOcPw() == null || onlineComplaint.getOcPw().isEmpty()) {
            bindingResult.rejectValue("ocPw", "error.ocPw", "비밀번호가 필요합니다.");
        }
        if (onlineComplaint.getOcName() == null || onlineComplaint.getOcName().isEmpty()) {
            bindingResult.rejectValue("ocName", "error.ocName", "이름이 필요합니다.");
        }
        if (onlineComplaint.getOcAdvisor() == null || onlineComplaint.getOcAdvisor().isEmpty()) {
            bindingResult.rejectValue("ocAdvisor", "error.ocAdvisor", "도움 받고 싶은 사람을 선택해 주세요.");
        }
        if (onlineComplaint.getOcTitle() == null || onlineComplaint.getOcTitle().isEmpty()) {
            bindingResult.rejectValue("ocTitle", "error.ocTitle", "제목이 필요합니다.");
        }
        if (onlineComplaint.getOcContent() == null || onlineComplaint.getOcContent().isEmpty()) {
            bindingResult.rejectValue("ocContent", "error.ocContent", "내용이 필요합니다.");
        }

    }

    public void saveOnlineComplaint(OnlineComplaint onlineComplaint) {
        onlineComplaint.setOcResponseStatus("답변대기");
        LocalDateTime currentDateTime = LocalDateTime.now();
        onlineComplaint.setOcDate(currentDateTime);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDateTime.format(dateFormatter);
        onlineComplaint.setOcDateFormatted(formattedDate);

        onlineComplaintRepository.save(onlineComplaint);
    }

    public void updateAdminOnlineComplaint(Integer aId, Integer ocId, String ocResponseContent) {
        OnlineComplaint onlineComplaint = getComplaintById(ocId);
        if (onlineComplaint != null) {
            onlineComplaint.setAId(aId);
            onlineComplaint.setOcResponseContent(ocResponseContent);

            onlineComplaint.setOcResponseStatus("답변완료");

            onlineComplaintRepository.save(onlineComplaint);
        }
    }
}
