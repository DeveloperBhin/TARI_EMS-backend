package com.ems.admin.model;

import jakarta.persistence.*;

@Entity
@Table(name = "event_registration")
public class EventRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    // =========================
    // BASIC INFO
    // =========================
    private String fullname;
    private String phone;
    private String email;

    // =========================
    // PERSONAL INFO
    // =========================
    private String age;
    private String country;
    private String region;
    private String gender;

    // =========================
    // EVENT INFO
    // =========================
    @Column(name = "shirt_size")
    private String shirtSize;

    @Column(name = "race_type")
    private String raceType;

    @Column(name = "group_name")
    private String groupName;

    // =========================
    // PICKUP LOCATION
    // =========================
    @Column(name = "pickup_location")
    private String pickupLocation;

    // =========================
    // IDENTIFICATION
    // =========================
    @Column(name = "id_type")
    private String idType;

    @Column(name = "id_number")
    private String idNumber;

    // =========================
    // COMPANION INFO
    // =========================
    @Column(name = "fellowname")   // IMPORTANT FIX
    private String fellowName;

    @Column(name = "fellowphone")  // IMPORTANT FIX
    private String fellowPhone;

    // =========================
    // PAYMENT
    // =========================
    @Column(name = "proof_of_payment")
    private String proofOfPayment;

    // =========================
    // STATUS
    // =========================
    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        PENDING,
        COMPLETED,
        FAILED,
        REJECTED
    }

    // Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getShirtSize() {
        return shirtSize;
    }

    public void setShirtSize(String shirtSize) {
        this.shirtSize = shirtSize;
    }

    public String getRaceType() {
        return raceType;
    }

    public void setRaceType(String raceType) {
        this.raceType = raceType;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getProofOfPayment() {
        return proofOfPayment;
    }

    public void setProofOfPayment(String proofOfPayment) {
        this.proofOfPayment = proofOfPayment;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Event getEvent() {
    return event;
}

public void setEvent(Event event) {
    this.event = event;
}



public String getCountry() {
    return country;

}

public void setCountry(String country) {
    this.country = country;

}

public String getRegion() {
    return region;
}

public void setRegion(String region) {
    this.region = region;
}

public String getGender() {
    return gender;
}

public void setGender(String gender) {
    this.gender = gender;
}

public String getGroupName() {
    return groupName;
}

public void setGroupName(String groupName) {
    this.groupName = groupName;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }
    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

      public String getFellowName() {
        return fellowName;
    }

    public void setFellowName(String fellowName) {
        this.fellowName = fellowName;
    }

    public String getFellowPhone() {
        return fellowPhone;
    }

    public void setFellowPhone(String fellowPhone) {
        this.fellowPhone = fellowPhone;
    }

    

    

}
