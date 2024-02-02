package com.mrxacx.spacefleet.controller;

import com.mrxacx.spacefleet.controller.dto.impl.MaintenanceDTO;
import com.mrxacx.spacefleet.controller.dto.impl.RepairDTO;
import com.mrxacx.spacefleet.controller.dto.impl.SpaceshipDTO;
import com.mrxacx.spacefleet.enumerate.SearchSpaceshipParam;
import com.mrxacx.spacefleet.model.Maintenance;
import com.mrxacx.spacefleet.model.Repair;
import com.mrxacx.spacefleet.model.Spaceship;
import com.mrxacx.spacefleet.service.impl.SpaceshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/spaceship")
@RequiredArgsConstructor
@RestController
public class SpaceshipController {
  final SpaceshipService spaceshipService;
  
  @GetMapping("/{id}")
  public Spaceship fetchSpaceship(@PathVariable("id") String id) {
    try {
      return spaceshipService
          .fetchSpaceship(
              UUID.fromString(id)
          );
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    }
  }
  
  @PostMapping
  public Spaceship recordSpaceship(@RequestBody SpaceshipDTO spaceshipDTO) {
    try {
      return spaceshipService
          .recordSpaceship(spaceshipDTO.getModel());
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    }
  }
  
  @GetMapping("/search/{method}")
  public List<Spaceship> fetchSpaceships(@PathVariable("method") String searchParam, @RequestParam("w") String searchValue) {
    List<Spaceship> spaceships = null;
    try {
      switch (SearchSpaceshipParam.valueOf(searchParam.toUpperCase())) { // Select the correct search service
        case NAME -> spaceships = spaceshipService.fetchSpaceshipsForName(searchValue);
        case MANUFACTURER -> spaceships = spaceshipService.fetchSpaceshipsForManufacturer(searchValue);
        case MODEL -> spaceships = spaceshipService.fetchSpaceshipsForModel(searchValue);
      }
      
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    
    return spaceships;
  }
  
  @PatchMapping("/{id}")
  public Spaceship updateSpaceship(@PathVariable("id") String spaceship, @RequestBody SpaceshipDTO spaceshipDTO) {
    return spaceshipService.updateSpaceship(UUID.fromString(spaceship), spaceshipDTO);
  }
  
  @DeleteMapping("/{id}")
  public void removeSpaceship(@PathVariable("id") String spaceship) {
    spaceshipService.removeSpaceship(UUID.fromString(spaceship));
  }
  
  @PostMapping("/maintenance")
  public Maintenance recordMaintenance(@RequestBody MaintenanceDTO maintenanceDTO) {
    return spaceshipService.recordSpaceshipMaintenance(maintenanceDTO);
  }
  
  @PostMapping("/repair")
  public Repair recordRepair(@RequestBody RepairDTO repairDTO) {
    return spaceshipService.recordSpaceshipRepair(repairDTO);
  }
  
  @PatchMapping("/repair")
  public Repair finishRepair(@RequestBody RepairDTO repairDTO) {
    return spaceshipService.finishSpaceshipRepair(repairDTO.getId());
  }
}