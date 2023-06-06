package com.sos.signal.policecomplaint.repository;

import com.sos.signal.onlinecomplaint.OnlineComplaint;
import com.sos.signal.policecomplaint.dto.PoliceComplaint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Repository interface for database operations
public interface PoliceComplaintRepository extends JpaRepository<PoliceComplaint, Integer> {
    // Add any custom methods if needed
    List<PoliceComplaint> findByPcTitleContainingIgnoreCase(String query);

}
