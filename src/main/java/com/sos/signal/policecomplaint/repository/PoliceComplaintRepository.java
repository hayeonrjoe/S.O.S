package com.sos.signal.policecomplaint.repository;

import com.sos.signal.onlinecomplaint.OnlineComplaint;
import com.sos.signal.policecomplaint.dto.PoliceComplaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// Repository interface for database operations
public interface PoliceComplaintRepository extends JpaRepository<PoliceComplaint, Integer> {
    // Add any custom methods if needed
    List<PoliceComplaint> findByPcTitleContainingIgnoreCase(String query);

    @Query("SELECT new com.sos.signal.policecomplaint.dto.PoliceComplaint(pc.pcPw, pc.pcId, pc.pcTitle, pc" +
            ".pcName, " +
            "COALESCE(pc.pcDateFormatted, DATE_FORMAT(pc.pcDate, '%Y-%m-%d')), " +
            "pc.pcResponseStatus) " +
            "FROM PoliceComplaint pc " +
            "ORDER BY pc.pcId DESC")
    List<PoliceComplaint> findLatestResults();

}
