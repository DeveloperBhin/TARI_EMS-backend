package com.ems.admin.service;

import com.ems.admin.model.Event;
import com.ems.admin.repository.EventRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class EventService{

    private final EventRepository repository;

    public EventService(EventRepository repository){
        this.repository = repository;
    } 

    public Event createEvent(Event event) {
        return repository.save(event);
    }

    public List<Event> getAllEvents(){
        return repository.findAll();
    }

   public Event getEventById(Long id) {
    return repository.findById(id).orElse(null);
}

   public Event updateEvent(Long id, Event updateEvent){
    updateEvent.setId(id);
    return repository.save(updateEvent);

   }

   public void deleteEvent(Long id){
    repository.deleteById(id);
   }

}