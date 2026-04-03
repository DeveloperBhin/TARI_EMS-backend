package com.ems.admin.repository;

import com.ems.admin.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EventRepository extends JpaRepository<Event, Long>{

}