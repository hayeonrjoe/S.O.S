package com.sos.signal.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    @Query("select a from Admin a where a.a_email = :email and a.a_pw = :pw")
    Admin findMember(@Param("email") String a_email, @Param("pw") String a_pw);
}
