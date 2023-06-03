package com.sos.signal.counselingappointment;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import java.time.LocalDateTime;

@Service
public class CounselingAppointmentService {

    private final CounselingAppointmentRepository counselingAppointmentRepository;

    public CounselingAppointmentService(CounselingAppointmentRepository counselingAppointmentRepository) {
        this.counselingAppointmentRepository = counselingAppointmentRepository;
    }

    public void validateCounselingAppointment(CounselingAppointment counselingAppointment, BindingResult bindingResult) {
        if (counselingAppointment.getCa_name() == null || counselingAppointment.getCa_name().isEmpty()) {
            bindingResult.rejectValue("ca_name", "error.ca_name", "이름이 필요합니다.");
        }
        if (counselingAppointment.getCa_contact() == null || counselingAppointment.getCa_contact().isEmpty()) {
            bindingResult.rejectValue("ca_contact", "error.ca_contact", "연락처가 필요합니다.");
        }
        if (counselingAppointment.getCa_area() == null || counselingAppointment.getCa_area().isEmpty()) {
            bindingResult.rejectValue("ca_area", "error.ca_area", "지역을 선택해주세요.");
        }
        if (counselingAppointment.getCa_place() == null || counselingAppointment.getCa_place().isEmpty()) {
            bindingResult.rejectValue("ca_place", "error.ca_place", "장소를 선택해주세요.");
        }
        if (counselingAppointment.getCa_scheduled_date() == null) {
            bindingResult.rejectValue("ca_scheduled_date", "error.ca_scheduled_date", "희망하는 날짜와 시간을 선택해주세요.");
        }
    }

    public void saveCounselingAppointment(CounselingAppointment counselingAppointment) {
        counselingAppointment.setCa_response_status("답변대기");
        counselingAppointment.setCa_date(LocalDateTime.now());
        counselingAppointmentRepository.save(counselingAppointment);
    }
}
