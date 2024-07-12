package com.example.projectbase.repository;
import com.example.projectbase.domain.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OtpRepository extends JpaRepository<Otp, Long>{
    Optional<Otp> findByUsernameAndOtp(String username, String otp);
    void deleteByUsername(String username);
}
