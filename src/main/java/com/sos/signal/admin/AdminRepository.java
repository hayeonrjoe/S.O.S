package com.sos.signal.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    @Query("SELECT a FROM Admin a WHERE a.a_email = :email AND a.a_pw = :pw")
    List<Admin> findMembers(@Param("email") String email, @Param("pw") String pw);

}
