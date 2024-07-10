package com.example.projectbase.repository;

import com.example.projectbase.domain.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;

import java.util.Date;

@Repository
public interface EventRepository extends JpaRepository<Event, String> {
    Event findByType(String type);

    @Query("SELECT e FROM Event e WHERE e.startDate >= ?1 and e.startDate <= ?2")
    Event findByStartDate(Date startDate1, Date startDate2);
    Page<Event> findAll(org.springframework.data.domain.Pageable pageable);
}
