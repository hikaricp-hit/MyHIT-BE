package com.example.projectbase.repository;


import com.example.projectbase.domain.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, String> {
    @Query("SELECT e FROM Event e WHERE e.type = ?1")
    List<Event> findByType(String type);

    @Query("SELECT e FROM Event e WHERE e.startDate = ?1")
    List<Event> findByDate(String startDate);
}
