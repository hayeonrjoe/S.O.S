package com.sos.signal.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    @Query("SELECT a FROM Admin a WHERE a.aEmail = :email AND a.a_pw = :pw")
    List<Admin> findMembers(@Param("email") String email, @Param("pw") String pw);

    Admin findByaEmail(String aEmail);

    Admin findByaId(Integer aId);
<<<<<<< HEAD
=======

>>>>>>> baaf3847bbda343a1b2d5a20d31f41ccd3e0c906
}
