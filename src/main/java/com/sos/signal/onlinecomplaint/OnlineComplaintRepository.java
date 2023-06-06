package com.sos.signal.onlinecomplaint;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Repository interface for database operations
public interface OnlineComplaintRepository extends JpaRepository<OnlineComplaint, Integer> {
    List<OnlineComplaint> findByOcTitleContainingIgnoreCase(String query);

}
