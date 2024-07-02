package com.example.projectbase.repository;


import com.example.projectbase.domain.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, String> {
    @Query("SELECT e FROM Event e WHERE e.type = ?1")
    Page<Event> findByType(String type, Pageable pageable);

    @Query("SELECT e FROM Event e WHERE e.startDate = ?1")
    Page<Event> findByDate(Date startDate, Pageable pageable);

    Page<Event> findAll(Pageable pageable);
}
