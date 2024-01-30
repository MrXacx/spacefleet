package com.mrxacx.spacefleet.repository;

import com.mrxacx.spacefleet.model.Journey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IJourneyRepository extends JpaRepository<Journey, UUID> {
}