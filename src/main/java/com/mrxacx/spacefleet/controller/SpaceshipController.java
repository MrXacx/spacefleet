package com.mrxacx.spacefleet.controller;

import com.mrxacx.spacefleet.controller.dto.impl.SpaceshipDTO;
import com.mrxacx.spacefleet.enumerate.SearchSpaceshipParam;
import com.mrxacx.spacefleet.model.Spaceship;
import com.mrxacx.spacefleet.service.impl.SpaceshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
}
