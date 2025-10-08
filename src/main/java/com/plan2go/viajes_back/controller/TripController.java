package com.plan2go.viajes_back.controller;

import com.plan2go.viajes_back.entity.Trip;
import com.plan2go.viajes_back.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class TripController {
    private final TripRepository trips;

    @GetMapping("/api/trips/owner/{ownerId}")
    public List<Trip> byOwner(@PathVariable UUID ownerId){
        return trips.findByOwner_Id(ownerId);
    }
}
