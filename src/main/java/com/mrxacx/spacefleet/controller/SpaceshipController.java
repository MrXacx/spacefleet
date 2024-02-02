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
          .fetch(
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
          .record(spaceshipDTO.getModel());
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
        case NAME -> spaceships = spaceshipService.fetchForName(searchValue);
        case MANUFACTURER -> spaceships = spaceshipService.fetchForManufacturer(searchValue);
        case MODEL -> spaceships = spaceshipService.fetchForModel(searchValue);
      }
      
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    
    return spaceships;
  }
  
  @PatchMapping("/{id}")
  public Spaceship updateSpaceship(@PathVariable("id") String spaceship, @RequestBody SpaceshipDTO spaceshipDTO) {
    return spaceshipService.update(UUID.fromString(spaceship), spaceshipDTO);
  }
  
  @DeleteMapping("/{id}")
  public void removeSpaceship(@PathVariable("id") String spaceship) {
    spaceshipService.remove(UUID.fromString(spaceship));
  }
  
  @PostMapping("/maintenance")
  public Maintenance recordMaintenance(@RequestBody MaintenanceDTO maintenanceDTO) {
    return spaceshipService.recordMaintenance(maintenanceDTO);
  }
  
  @PostMapping("/repair")
  public Repair recordRepair(@RequestBody RepairDTO repairDTO) {
    return spaceshipService.recordRepair(repairDTO);
  }
  
  @PatchMapping("/repair")
  public Repair finishRepair(@RequestBody RepairDTO repairDTO) {
    return spaceshipService.finishRepair(repairDTO.getId());
  }
}