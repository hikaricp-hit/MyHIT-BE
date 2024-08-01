package com.example.projectbase.repository;

import com.example.projectbase.domain.entity.Course;
import com.example.projectbase.domain.entity.Member;
import com.example.projectbase.domain.entity.Register;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface RegisterRepository extends JpaRepository<Register, String> {
    Page<Register> findAll(org.springframework.data.domain.Pageable pageable);
    Page<Register> findRegistersBySubscriberFullName(String subscriberFullName, org.springframework.data.domain.Pageable pageable);

    @Query("SELECT COUNT(r) > 0 FROM Register r WHERE r.course.id = :courseId AND r.subscriber.id = :memberId")
    boolean existsBySubscriberAndCourse(@Param("courseId") String courseId, @Param("memberId") String memberId);


    long countBySubscriber(Member member);
}
