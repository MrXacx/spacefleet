package com.mrxacx.spacefleet.service;

import com.mrxacx.spacefleet.model.Maintenance;
import com.mrxacx.spacefleet.model.Repair;
import com.mrxacx.spacefleet.model.Spaceship;
import com.mrxacx.spacefleet.controller.dto.RepairDTO;

import java.util.List;
import java.util.UUID;

/**
 * @author ariel
 * @since 1.0
 */
public interface ISpaceshipService {
  Spaceship recordSpaceship(String model);
  
  Spaceship fetchSpaceship(UUID spaceshipId);
  
  List<Spaceship> fetchSpaceshipsForName(String name);
  
  List<Spaceship> fetchSpaceshipsForModel(String model);
  
  List<Spaceship> fetchSpaceshipsForManufacturer(String manufacturer);
  
  Repair recordSpaceshipRepair(RepairDTO repairDTO);
  
  Repair fetchSpaceshipRepair(UUID repairId);
  
  Repair finishSpaceshipRepair(UUID repairId);
  
  Maintenance recordSpaceshipMaintenance(UUID spaceshipId);
  
  
}
