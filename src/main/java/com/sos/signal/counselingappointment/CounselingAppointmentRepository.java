package com.sos.signal.counselingappointment;


import org.springframework.data.jpa.repository.JpaRepository;

// Repository interface for database operations
public interface CounselingAppointmentRepository extends JpaRepository<CounselingAppointment, Integer> {
    // Add any custom methods if needed
}
