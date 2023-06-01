package com.sos.signal.onlinecomplaint;

import org.springframework.data.jpa.repository.JpaRepository;

// Repository interface for database operations
public interface OnlineComplaintRepository extends JpaRepository<OnlineComplaint, Long> {
    // Add any custom methods if needed
}
