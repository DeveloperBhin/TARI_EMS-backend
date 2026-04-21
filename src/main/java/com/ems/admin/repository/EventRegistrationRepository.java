package com.ems.admin.repository;

import com.ems.admin.model.EventRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;


public interface EventRegistrationRepository extends JpaRepository<EventRegistration, Long>{


}