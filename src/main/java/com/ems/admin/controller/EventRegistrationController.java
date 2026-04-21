package com.ems.admin.controller;

import com.ems.admin.model.EventRegistration;
import com.ems.admin.service.EventRegistrationService;
import com.ems.admin.model.Event;
import com.ems.admin.service.EventService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.*;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/admin/registrations") 
@CrossOrigin(origins = "https://events.tari.go.tz/")
public class EventRegistrationController {

    private final EventRegistrationService service;
    private final EventService eventService;

    public EventRegistrationController(
            EventRegistrationService service,
            EventService eventService
    ){
        this.service = service;
        this.eventService = eventService;
    }
  

    // ✅ CREATE
    // @PostMapping
    // public EventRegistration create(@RequestBody EventRegistration reg) {
    //     reg.setStatus(EventRegistration.Status.PENDING);
    //     return service.createEventRegistration(reg);
    // }

    @PostMapping
public EventRegistration create(
        @RequestBody EventRegistration reg,
        @RequestParam Long eventId
) {
    Event event = eventService.getEventById(eventId); // you need this service
    reg.setEvent(event);
    reg.setStatus(EventRegistration.Status.PENDING);

    return service.createEventRegistration(reg);
}

    // ✅ GET ALL
    @GetMapping
    public List<EventRegistration> getAll(){
        return service.getAllEventRegistrations();
    }

    // ✅ GET ONE
    @GetMapping("/{id}")
    public EventRegistration getById(@PathVariable Long id){
        return service.getEventRegistrationById(id);
    }

    // ✅ UPDATE
    @PutMapping("/{id}")
    public EventRegistration update(@PathVariable Long id, @RequestBody EventRegistration reg){
        return service.updateEventRegistration(id, reg);
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        service.deleteEventRegistration(id);
        return "Deleted successfully";
    }

    // ✅ FILE UPLOAD
    @PostMapping("/upload")
    public String uploadProof(@RequestParam("file") MultipartFile file) {
        try {
            String uploadDir = "upload/";

            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);

            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            return "https://events.tari.go.tz/upload/" + fileName;

        } catch (IOException e) {
            e.printStackTrace();
            return "Upload failed";
        }
    }

    @PostMapping("/complete/{id}")
public EventRegistration completeRegistration(
        @PathVariable Long id,
        @RequestParam("file") MultipartFile file
) {
    try {
        String uploadDir = "upload/";

        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);

        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        String fileUrl = "https://events.tari.go.tz/upload/" + fileName;

        // 🔹 Get existing registration
        EventRegistration reg = service.getEventRegistrationById(id);

        // 🔹 Update fields
        reg.setProofOfPayment(fileUrl);
        reg.setStatus(EventRegistration.Status.COMPLETED);

        // 🔹 Save updated record
        return service.updateEventRegistration(id, reg);

    } catch (IOException e) {
        e.printStackTrace();
        throw new RuntimeException("File upload failed");
    }
}

 
}