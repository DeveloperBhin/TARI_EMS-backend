package com.ems.admin.controller;

import com.ems.admin.model.Event;
import com.ems.admin.model.EventRegistration;
import com.ems.admin.service.EventRegistrationService;
import com.ems.admin.service.EventService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

@RestController
@RequestMapping("api/admin/registrations")
public class EventRegistrationController {

    private final EventRegistrationService service;
    private final EventService eventService;

    public EventRegistrationController(
            EventRegistrationService service,
            EventService eventService
    ) {
        this.service = service;
        this.eventService = eventService;
    }

    // =========================
    // CREATE REGISTRATION + FILE
    // =========================
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public EventRegistration create(
            @RequestParam Long eventId,
            @RequestParam String fullname,
            @RequestParam String phone,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String yearOfBirth,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String region,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) String shirtSize,
            @RequestParam(required = false) String raceType,
            @RequestParam(required = false) String pickupLocation,
            @RequestParam(required = false) String groupName,
            @RequestParam(required = false) String idType,
            @RequestParam(required = false) String idNumber,
            @RequestParam(required = false) Double donationAmount,
            @RequestParam(required = false) String registrationType,
            @RequestParam MultipartFile proofOfPayment
    ) throws IOException {

        Event event = eventService.getEventById(eventId);

        // =========================
        // FILE UPLOAD
        // =========================
        String uploadDir = "upload/proofs/";
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String fileName = System.currentTimeMillis()
                + "_" + proofOfPayment.getOriginalFilename();

        Path filePath = uploadPath.resolve(fileName);

        Files.copy(
                proofOfPayment.getInputStream(),
                filePath,
                StandardCopyOption.REPLACE_EXISTING
        );

        String fileUrl = "https://events.tari.go.tz/upload/proofs/" + fileName;

        // =========================
        // BUILD ENTITY
        // =========================
        EventRegistration reg = new EventRegistration();

        reg.setEvent(event);
        reg.setFullname(fullname);
        reg.setPhone(phone);
        reg.setEmail(email);

        reg.setYearOfBirth(yearOfBirth);
        reg.setCountry(country);
        reg.setRegion(region);
        reg.setGender(gender);

        reg.setShirtSize(shirtSize);
        reg.setRaceType(raceType);
        reg.setPickupLocation(pickupLocation);
        reg.setGroupName(groupName);

        reg.setIdType(idType);
        reg.setIdNumber(idNumber);

        reg.setDonationAmount(donationAmount);
        reg.setRegistrationType(registrationType);

        reg.setProofOfPayment(fileUrl);
        reg.setStatus(EventRegistration.Status.PENDING);

        return service.createEventRegistration(reg);
    }

    // =========================
    // GET ALL
    // =========================
    @GetMapping
    public List<EventRegistration> getAll() {
        return service.getAllEventRegistrations();
    }

    // =========================
    // GET BY ID
    // =========================
    @GetMapping("/{id}")
    public EventRegistration getById(@PathVariable Long id) {
        return service.getEventRegistrationById(id);
    }

    // =========================
    // UPDATE
    // =========================
    @PutMapping("/{id}")
    public EventRegistration update(
            @PathVariable Long id,
            @RequestBody EventRegistration reg
    ) {
        return service.updateEventRegistration(id, reg);
    }

    // =========================
    // DELETE
    // =========================
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteEventRegistration(id);
        return "Deleted successfully";
    }

    // =========================
    // OPTIONAL: STANDALONE UPLOAD
    // =========================
    @PostMapping("/upload")
    public String uploadProof(@RequestParam("file") MultipartFile file) throws IOException {

        String uploadDir = "upload/proofs/";
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String fileName = System.currentTimeMillis()
                + "_" + file.getOriginalFilename();

        Path filePath = uploadPath.resolve(fileName);

        Files.copy(
                file.getInputStream(),
                filePath,
                StandardCopyOption.REPLACE_EXISTING
        );

        return "https://events.tari.go.tz/upload/proofs/" + fileName;
    }
}