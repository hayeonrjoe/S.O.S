package com.sos.signal.policecomplaint.repository;

import com.sos.signal.policecomplaint.dto.AttachmentFile;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository interface for database operations
public interface AttachmentFileRepository extends JpaRepository<AttachmentFile, Integer> {
    // Add any custom methods if needed
}
