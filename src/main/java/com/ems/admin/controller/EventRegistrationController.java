package com.ems.admin.controller;

import com.ems.admin.dto.requests.EventRegistrationRequest;
import com.ems.admin.dto.response.EventRegistrationResponse;
import com.ems.admin.mapper.EventRegistrationMapper;
import com.ems.admin.model.EventRegistration;
import com.ems.admin.service.EventRegistrationService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/admin/registrations")
public class EventRegistrationController {

    private final EventRegistrationService service;

    // SERVER UPLOAD DIRECTORY
    private static final String UPLOAD_DIR = "/home/tari01/upload/";

    public EventRegistrationController(EventRegistrationService service) {
        this.service = service;
    }

    // =========================
    // CREATE REGISTRATION
    // =========================
    @PostMapping
    public EventRegistrationResponse create(@RequestBody EventRegistrationRequest request) {

        System.out.println("Event ID = " + request.getEventId());
        System.out.println("Request = " + request);

        if (request.getEventId() == null) {
            throw new IllegalArgumentException("eventId is required");
        }

        EventRegistration saved = service.create(request);

        return EventRegistrationMapper.toResponse(saved);
    }

    // =========================
    // GET ALL
    // =========================
    @GetMapping
    public List<EventRegistrationResponse> getAll() {

        return service.getAllEventRegistrations()
                .stream()
                .map(EventRegistrationMapper::toResponse)
                .toList();
    }

    // =========================
    // GET BY ID
    // =========================
    @GetMapping("/{id}")
    public EventRegistrationResponse getById(@PathVariable Long id) {

        return EventRegistrationMapper.toResponse(
                service.getEventRegistrationById(id)
        );
    }

    // =========================
    // UPDATE
    // =========================
    @PutMapping("/{id}")
    public EventRegistrationResponse update(
            @PathVariable Long id,
            @RequestBody EventRegistrationRequest request
    ) {

        EventRegistration updated = service.update(id, request);

        return EventRegistrationMapper.toResponse(updated);
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
    // FILE UPLOAD (NEW PART)
    // =========================
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
    System.out.println("🔥 UPLOAD ENDPOINT HIT");
    System.out.println("File present = " + (file != null));
System.out.println("File name = " + file.getOriginalFilename());
System.out.println("File size = " + file.getSize());
    
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("File is empty");
            }

            // create folder if not exists
            File dir = new File(UPLOAD_DIR);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // unique file name
            String fileName =
                    System.currentTimeMillis() + "_" + file.getOriginalFilename();

            Path path = Paths.get(UPLOAD_DIR + fileName);

            Files.write(path, file.getBytes());

            // public URL (frontend uses this)
            String fileUrl =
                    "https://events.tari.go.tz/uploads/" + fileName;

            return fileUrl;

        } catch (Exception e) {
            throw new RuntimeException("Upload failed: " + e.getMessage());
        }
    }
}