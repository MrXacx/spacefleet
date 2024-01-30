package com.mrxacx.spacefleet.repository;

import com.mrxacx.spacefleet.model.Repair;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IRepairRepository extends JpaRepository<Repair, UUID> {
}