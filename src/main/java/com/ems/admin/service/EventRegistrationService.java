package com.ems.admin.service;

import com.ems.admin.dto.requests.EventRegistrationRequest;
import com.ems.admin.model.Event;
import com.ems.admin.model.EventRegistration;
import com.ems.admin.repository.EventRegistrationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventRegistrationService {

    private static final Logger log =
            LoggerFactory.getLogger(EventRegistrationService.class);

    private final EventRegistrationRepository repository;
    private final EventService eventService;

    public EventRegistrationService(
            EventRegistrationRepository repository,
            EventService eventService
    ) {
        this.repository = repository;
        this.eventService = eventService;
    }

    // =========================
    // CREATE
    // =========================
    public EventRegistration create(EventRegistrationRequest request) {

        EventRegistration reg = new EventRegistration();

        Event event = eventService.getEventById(request.getEventId());
        if (event == null) {
            throw new RuntimeException("Event not found");
        }

        reg.setEvent(event);

        reg.setFullname(request.getFullname());
        reg.setPhone(request.getPhone());
        reg.setEmail(request.getEmail());

        reg.setCountry(request.getCountry());
        reg.setRegion(request.getRegion());
        reg.setGender(request.getGender());

        reg.setShirtSize(request.getShirtSize());
        reg.setRaceType(request.getRaceType());
        reg.setPickupLocation(request.getPickupLocation());
        reg.setGroupName(request.getGroupName());

        reg.setIdType(request.getIdType());
        reg.setIdNumber(request.getIdNumber());

        reg.setFellowName(request.getFellowName());
        reg.setFellowPhone(request.getFellowPhone());

        reg.setProofOfPayment(request.getProofOfPayment());

        reg.setStatus(EventRegistration.Status.PENDING);

        // =========================
        // LOGGING (CREATE)
        // =========================
        log.info("===== CREATE REGISTRATION =====");

        log.info("Basic -> fullname={}, phone={}, email={}",
                reg.getFullname(), reg.getPhone(), reg.getEmail());

        log.info("Personal -> country={}, region={}, gender={}",
                reg.getCountry(), reg.getRegion(), reg.getGender());

        log.info("Event -> eventId={}",
                reg.getEvent() != null ? reg.getEvent().getId() : null);

        log.info("Race -> shirtSize={}, raceType={}, groupName={}",
                reg.getShirtSize(), reg.getRaceType(), reg.getGroupName());

        log.info("Pickup -> location={}", reg.getPickupLocation());

        log.info("ID -> type={}, number={}",
                reg.getIdType(), reg.getIdNumber());

        log.info("Fellow -> name={}, phone={}",
                reg.getFellowName(), reg.getFellowPhone());

        log.info("Payment -> proofOfPayment={}", reg.getProofOfPayment());

        log.info("Status -> {}", reg.getStatus());
        

        log.info("===== END CREATE =====");

        return repository.save(reg);
    }

    // =========================
    // GET ALL
    // =========================
    public List<EventRegistration> getAllEventRegistrations() {
        log.info("Fetching all event registrations");
        return repository.findAll();
    }

    // =========================
    // GET BY ID
    // =========================
    public EventRegistration getEventRegistrationById(Long id) {

        log.info("Fetching registration by id={}", id);

        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registration not found"));
    }

    // =========================
    // UPDATE
    // =========================
    public EventRegistration update(Long id, EventRegistrationRequest request) {

        EventRegistration reg = getEventRegistrationById(id);

        reg.setFullname(request.getFullname());
        reg.setPhone(request.getPhone());
        reg.setEmail(request.getEmail());

        reg.setCountry(request.getCountry());
        reg.setRegion(request.getRegion());
        reg.setGender(request.getGender());

        reg.setShirtSize(request.getShirtSize());
        reg.setRaceType(request.getRaceType());
        reg.setPickupLocation(request.getPickupLocation());
        reg.setGroupName(request.getGroupName());

        reg.setIdType(request.getIdType());
        reg.setIdNumber(request.getIdNumber());

        reg.setFellowName(request.getFellowName());
        reg.setFellowPhone(request.getFellowPhone());

        reg.setProofOfPayment(request.getProofOfPayment());

        Event event = eventService.getEventById(request.getEventId());
        if (event != null) {
            reg.setEvent(event);
        }

        log.info("===== UPDATE REGISTRATION =====");
        log.info("ID -> {}", id);
        log.info("Updated -> fullname={}, phone={}, email={}",
                reg.getFullname(), reg.getPhone(), reg.getEmail());
        log.info("Updated Event -> {}",
                reg.getEvent() != null ? reg.getEvent().getId() : null);
        log.info("===== END UPDATE =====");

        return repository.save(reg);
    }

    // =========================
    // DELETE
    // =========================
    public void deleteEventRegistration(Long id) {

        log.info("Deleting registration id={}", id);

        repository.deleteById(id);

        log.info("Deleted registration id={}", id);
    }
}