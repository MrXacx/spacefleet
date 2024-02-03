package com.mrxacx.spacefleet.controller;

import com.mrxacx.spacefleet.controller.dto.impl.JourneyDTO;
import com.mrxacx.spacefleet.model.Journey;
import com.mrxacx.spacefleet.service.impl.JourneyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/journey")
@RequiredArgsConstructor
public class JourneyController {
  final private JourneyService journeyService;
  
  @GetMapping("/{id}")
  public Journey fetchJourney(@PathVariable("id") UUID journeyId) {
    return journeyService.fetch(journeyId);
  }
  
  @PostMapping()
  public Journey fetchJourney(@RequestBody JourneyDTO journeyDTO) {
    return journeyService.record(journeyDTO);
  }
  
  @PatchMapping("/{id}")
  public Journey finishJourney(@PathVariable("id") UUID journeyId) { return journeyService.finish(journeyId); }
}
