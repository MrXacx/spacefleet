package com.mrxacx.spacefleet.repository;

import com.mrxacx.spacefleet.model.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IMaintenanceRepository extends JpaRepository<Maintenance, UUID> {
}