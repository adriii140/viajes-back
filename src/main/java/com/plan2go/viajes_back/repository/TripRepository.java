package com.plan2go.viajes_back.repository;

import com.plan2go.viajes_back.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TripRepository extends JpaRepository<Trip, UUID> {
    List<Trip> findByOwner_Id(UUID ownerId);
}
