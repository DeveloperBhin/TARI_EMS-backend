package com.ems.admin.mapper;

import com.ems.admin.dto.response.EventRegistrationResponse;
import com.ems.admin.model.EventRegistration;

public class EventRegistrationMapper {

    public static EventRegistrationResponse toResponse(EventRegistration reg) {

        EventRegistrationResponse res = new EventRegistrationResponse();

        res.setId(reg.getId());

        res.setEventId(
            reg.getEvent() != null ? reg.getEvent().getId() : null
        );

        res.setFullname(reg.getFullname());
        res.setPhone(reg.getPhone());
        res.setEmail(reg.getEmail());

        res.setCountry(reg.getCountry());
        res.setRegion(reg.getRegion());
        res.setGender(reg.getGender());

        res.setShirtSize(reg.getShirtSize());
        res.setRaceType(reg.getRaceType());
        res.setPickupLocation(reg.getPickupLocation());
        res.setGroupName(reg.getGroupName());

        res.setIdType(reg.getIdType());
        res.setIdNumber(reg.getIdNumber());

        res.setFellowName(reg.getFellowName());
        res.setFellowPhone(reg.getFellowPhone());

        res.setProofOfPayment(reg.getProofOfPayment());

        res.setStatus(
            reg.getStatus() != null ? reg.getStatus().name() : null
        );

        return res;
    }
}

