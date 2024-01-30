package com.mrxacx.spacefleet.resource.dto;

import com.mrxacx.spacefleet.model.Spaceship;
import com.mrxacx.spacefleet.service.impl.SpaceshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/spaceship")
@RestController()
@RequiredArgsConstructor
public class SpaceshipController {
  final SpaceshipService spaceshipService;
  
  @GetMapping("/{id}")
  public Spaceship fetchSpaceship(@String id){
  
  }
}
