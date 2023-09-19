package com.sos.signal.onlinecomplaint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OnlineComplaintRepository extends JpaRepository<OnlineComplaint, Integer> {


    @Query("SELECT new com.sos.signal.onlinecomplaint.OnlineComplaint(oc.ocPw, oc.ocId, oc.ocTitle, oc.ocAdvisor, oc.ocName, " +
            "COALESCE(oc.ocDateFormatted, DATE_FORMAT(oc.ocDate, '%Y-%m-%d')), " +
            "oc.ocResponseStatus) " +
            "FROM OnlineComplaint oc " +
            "ORDER BY oc.ocId DESC")
    List<OnlineComplaint> findLatestResults();

    List<OnlineComplaint> findByOcTitleContainingIgnoreCase(String query);

    OnlineComplaint findByOcId(int ocId);

    List<OnlineComplaint> findByOcTitleAndOcAdvisor(String title, String ocAdvisor);

}
