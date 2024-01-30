package com.mrxacx.spacefleet.service;

import com.mrxacx.spacefleet.model.Maintenance;
import com.mrxacx.spacefleet.model.Repair;
import com.mrxacx.spacefleet.model.Spaceship;
import com.mrxacx.spacefleet.resource.dto.RepairDTO;

import java.util.List;

/**
 * @author ariel
 * @since 1.0
 */
public interface ISpaceshipService {
  Spaceship recordSpaceship(String model);
  
  Spaceship fetchSpaceship(String spaceshipId);
  
  List<Spaceship> fetchSpaceshipsForName(String name);
  
  List<Spaceship> fetchSpaceshipsForModel(String model);
  
  List<Spaceship> fetchSpaceshipsForManufacter(String manufacter);
  
  Repair recordSpaceshipRepair(RepairDTO repairDTO);
  
  Repair fetchSpaceshipRepair(String repairId);
  
  Repair finishSpaceshipRepair(String repairId);
  
  Maintenance recordSpaceshipMaintenance(String spaceshipId);
  
  
}
