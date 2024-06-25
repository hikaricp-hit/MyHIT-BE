package com.example.projectbase.repository;

import com.example.projectbase.domain.entity.Member_Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface Member_NotificationRepository extends JpaRepository<Member_Notification, String> {
    @Query("SELECT n from Member_Notification n where n.notifiedMember.id = ?1")
    Optional<List<Member_Notification>> findByMemberId(String memberId);
}
