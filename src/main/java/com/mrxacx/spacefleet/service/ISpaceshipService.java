package com.mrxacx.spacefleet.service;

import com.mrxacx.spacefleet.controller.dto.impl.MaintenanceDTO;
import com.mrxacx.spacefleet.controller.dto.impl.RepairDTO;
import com.mrxacx.spacefleet.controller.dto.impl.SpaceshipDTO;
import com.mrxacx.spacefleet.model.Maintenance;
import com.mrxacx.spacefleet.model.Repair;
import com.mrxacx.spacefleet.model.Spaceship;

import java.util.List;
import java.util.UUID;

/**
 * @author ariel
 * @since 1.0
 */

public interface ISpaceshipService {
  Spaceship record(String model);
  
  Spaceship fetch(UUID spaceshipId);
  
  List<Spaceship> fetchForName(String name);
  
  List<Spaceship> fetchForModel(String model);
  
  List<Spaceship> fetchForManufacturer(String manufacturer);
  
  Spaceship update(UUID spaceshipId, SpaceshipDTO spaceshipDTO);
  
  void remove(UUID spaceshipId);
  
  Repair recordRepair(RepairDTO repairDTO);
  
  Repair fetchRepair(UUID repairId);
  
  Repair finishRepair(UUID repairId);
  
  Maintenance recordMaintenance(MaintenanceDTO maintenanceDTO);
}