package com.sos.signal.onlinecomplaint;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class OnlineComplaintService {

    private final OnlineComplaintRepository onlineComplaintRepository;

    public OnlineComplaintService(OnlineComplaintRepository onlineComplaintRepository) {
        this.onlineComplaintRepository = onlineComplaintRepository;
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

    public void updatePAdminOnlineComplaint(Integer aId, Integer ocId, String ocResponseContent) {
        // Retrieve the OnlineComplaint entity from the repository based on ocId
        OnlineComplaint onlineComplaint = getComplaintById(ocId);
        if ((onlineComplaint != null) && (onlineComplaint.getOcAdvisor().equals("경찰"))) {
            // Update the aId and ocResponseStatus in the OnlineComplaint entity
            onlineComplaint.setAId(aId);
            onlineComplaint.setOcResponseContent(ocResponseContent);

            // Update the ocResponseStatus to "답변완료"
            onlineComplaint.setOcResponseStatus("답변완료");

            // Save the updated OnlineComplaint entity
            onlineComplaintRepository.save(onlineComplaint);
        }
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
        return onlineComplaintRepository.findById(ocId);
    }

    public OnlineComplaint findById(int ocId) {
        Optional<OnlineComplaint> result = Optional.ofNullable(onlineComplaintRepository.findById(ocId).orElse(null));
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
            // Handle other admin types or invalid admin types
            return Collections.emptyList(); // Return an empty list or handle it accordingly
        }
    }



    //////////////////////////////////////////////////////////////////////
//    Without Pagination
    public List<OnlineComplaint> getLatestResults() {
        return onlineComplaintRepository.findLatestResults();
    }

//////////////////////////////////////////////////////////////////////
//    Pagination
//    public List<OnlineComplaint> getLatestResults(int pageNumber, int pageSize) {
//        int startIndex = pageNumber * pageSize;
//        int endIndex = startIndex + pageSize;
//
//        List<OnlineComplaint> results = onlineComplaintRepository.findLatestResults();
//
//        if (startIndex >= results.size()) {
//            return Collections.emptyList();
//        }
//
//        // Adjust the end index if it exceeds the actual number of results
//        endIndex = Math.min(endIndex, results.size());
//
//        return results.subList(startIndex, endIndex);
//    }


}
