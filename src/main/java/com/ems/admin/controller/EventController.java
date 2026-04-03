package com.ems.admin.controller;

import com.ems.admin.model.Event;
import com.ems.admin.service.EventService;
import org.springframework.web.bind.annotation.*;


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

}