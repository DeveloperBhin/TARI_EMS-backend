package com.ems.admin.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Email;

@Entity
public class EventRegistration {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
@ManyToOne
@JoinColumn(name = "event_id", nullable = false)
private Event event;
@NotBlank(message = "Full name is required")
private String fullname;
@Pattern(
regexp = "^[0-9+\\-\\s]{9,15}$",
message = "Invalid phone number"
)
private String phone;
@Email(message = "Invalid email")
private String email;
// Personal Information
private String age;
private String yearOfBirth;
private String country;
private String region;
private String gender;
// Event Information
private String shirtSize;
private String raceType;
private String pickupLocation;
private String groupName;
// Identification
private String idType;
private String idNumber;
// Payment
private String proofOfPayment;
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

public String getYearOfBirth() {
    return yearOfBirth;
}

public void setYearOfBirth(String yearOfBirth) {
    this.yearOfBirth = yearOfBirth;
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

    public void setDonationAmount(Double donationAmount) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setDonationAmount'");
    }

    public void setRegistrationType(String registrationType) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setRegistrationType'");
    }

}
