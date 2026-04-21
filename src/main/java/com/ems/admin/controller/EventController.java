package com.ems.admin.controller;

import com.ems.admin.model.Event;
import com.ems.admin.service.EventService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;


import java.util.List;

@RestController
@RequestMapping("api/admin/events")

public class EventController {

    private final EventService service;

    public EventController(EventService service){
        this.service = service;
    }

    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        return service.createEvent(event);
    }

    @GetMapping
    public List<Event> getAllEvents(){
        return service.getAllEvents();

    }

    @GetMapping("/{id}")
    public Event getEvent(@PathVariable Long id){
        return service.getEventById(id);

    }

    @PutMapping("/{id}")
    public Event updateEvent(@PathVariable Long id, @RequestBody Event event){
        return service.updateEvent(id, event);
    }

    @DeleteMapping("/{id}")
    public String deleteEvent(@PathVariable Long id ){
        service.deleteEvent(id);
        return "Deleted succesfully";
    }

   @PostMapping("/upload")
public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {

    try {
        String uploadDir = "/home/tari01/upload/";

        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();

        Path path = Paths.get(uploadDir + filename);

        Files.createDirectories(path.getParent());
        Files.write(path, file.getBytes());

        String imageUrl = "https://41.59.225.78:8443/uploads/" + filename;

        return ResponseEntity.ok(imageUrl);

    } catch (Exception e) {
        return ResponseEntity.status(500).body("Upload failed: " + e.getMessage());
    }
}
}



