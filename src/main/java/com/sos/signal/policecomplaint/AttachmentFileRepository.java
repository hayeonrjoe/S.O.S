package com.sos.signal.policecomplaint;

import org.springframework.data.jpa.repository.JpaRepository;

// Repository interface for database operations
public interface AttachmentFileRepository extends JpaRepository<AttachmentFile, Long> {
    // Add any custom methods if needed
}
