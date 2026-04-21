package com.ems.admin.service;

import com.ems.admin.model.EventRegistration;
import com.ems.admin.repository.EventRegistrationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventRegistrationService {

    private final EventRegistrationRepository repository;

    public EventRegistrationService(EventRegistrationRepository repository){
        this.repository = repository;
    }

    // ✅ CREATE
    public EventRegistration createEventRegistration(EventRegistration eventRegistration) {
        return repository.save(eventRegistration);
    }

    // ✅ GET ALL
    public List<EventRegistration> getAllEventRegistrations(){
        return repository.findAll();
    }

    // ✅ GET BY ID
    public EventRegistration getEventRegistrationById(Long id) {
        return repository.findById(id).orElse(null);
    }

    // ✅ UPDATE (SAFE)
    public EventRegistration updateEventRegistration(Long id, EventRegistration updatedData){

        EventRegistration existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registration not found"));

        // 🔁 update fields
        existing.setFullname(updatedData.getFullname());
        existing.setPhone(updatedData.getPhone());
        existing.setEmail(updatedData.getEmail());
        existing.setAge(updatedData.getAge());
        existing.setShirtSize(updatedData.getShirtSize());
        existing.setRaceType(updatedData.getRaceType());
        existing.setLocation(updatedData.getLocation());
        existing.setProofOfPayment(updatedData.getProofOfPayment());
        existing.setStatus(updatedData.getStatus());

        return repository.save(existing);
    }

    // ✅ DELETE
    public void deleteEventRegistration(Long id){
        repository.deleteById(id);
    }
}