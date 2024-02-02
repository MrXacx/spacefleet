package com.mrxacx.spacefleet.service;

import com.mrxacx.spacefleet.controller.dto.impl.JourneyDTO;
import com.mrxacx.spacefleet.model.Journey;

import java.util.UUID;

public interface IJourneyService {
  Journey record(JourneyDTO journeyDTO);
  
  Journey fetch(UUID journeyId);
  
  Journey finishJourney(UUID journeyId);
  
}
