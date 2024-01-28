package com.mrxacx.spacefleet.service;

import com.mrxacx.spacefleet.resource.dto.RepairDTO;
import com.mrxacx.spacefleet.model.Maintenance;
import com.mrxacx.spacefleet.model.Repair;
import com.mrxacx.spacefleet.model.Spaceship;

import java.util.List;
import java.util.UUID;

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
