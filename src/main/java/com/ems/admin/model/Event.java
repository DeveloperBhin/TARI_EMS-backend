package com.ems.admin.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity

public class Event{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column (length=1000)
    private String description;

    private String category;

    private String featuredImage;

    private String eventLink;

    private LocalDate startDate;

    private LocalDate endDate;

    private String timezone;

    private String location;

    private String registrationRequired;

    private String eventType;

    private  LocalTime eventTime;



    // GETTERS & SETTERS

public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public String getTitle() {
    return title;
}

public void setTitle(String title) {
    this.title = title;
}

public String getDescription(){
    return description;
}

public void setDescription(String description){
    this.description = description;
}

public String getCategory(){
    return category;
}

public void setCategory(String category){
    this.category = category;
}

public String getFeaturedImage(){
    return featuredImage;
}
public void setFeaturedImage(String featuredImage){
    this.featuredImage = featuredImage;
}

public String getEventLink(){
    return eventLink;
}
public void setEventLink(String eventLink){
    this.eventLink = eventLink;
}

public LocalDate getStartDate(){
    return startDate;
}

public void setStartDate(LocalDate startDate){
    this.startDate = startDate;
}

public LocalDate getEndDate(){
    return endDate;
}

public void setEndDate(LocalDate endDate){
    this.endDate = endDate;

}

public String getTimezone(){
    return timezone;
}

public void setTimezone(String timezone){
    this.timezone = timezone;
}

public String getLocation(){
    return location;
}

public void setLocation(String location){
    this.location = location;
}

public String getRegistrationRequired(){
    return registrationRequired;
}
public void setRegistrationRequired(String registrationRequired){
    this.registrationRequired = registrationRequired;
}

public String getEventType(){
    return eventType;
}

public void setEventType(String eventType){
    this.eventType = eventType;
}

public LocalTime getEventTime(){
    return eventTime;
}

public void setEventTime(LocalTime eventTime){
    this.eventTime = eventTime;
}
}

