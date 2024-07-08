package com.example.projectbase.repository;

import com.example.projectbase.domain.entity.Register;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface RegisterRepository extends JpaRepository<Register, String> {
    Page<Register> findAll(org.springframework.data.domain.Pageable pageable);
    Page<Register> findRegistersBySubscriberFullName(String subscriberFullName, org.springframework.data.domain.Pageable pageable);
}
