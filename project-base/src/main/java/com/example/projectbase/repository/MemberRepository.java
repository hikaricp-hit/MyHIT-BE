package com.example.projectbase.repository;

import com.example.projectbase.domain.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
    Page<Member> findAll(org.springframework.data.domain.Pageable pageable);
    Optional<Member> findById(String id);
    Optional<Member> findMemberByEmail(String email);
}
