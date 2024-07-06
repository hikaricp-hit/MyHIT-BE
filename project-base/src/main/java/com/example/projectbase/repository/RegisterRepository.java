package com.example.projectbase.repository;

import com.example.projectbase.domain.entity.Register;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;

@Repository
public interface RegisterRepository extends JpaRepository<Register, String> {
    Page<Register> findAll(org.springframework.data.domain.Pageable pageable);
}
