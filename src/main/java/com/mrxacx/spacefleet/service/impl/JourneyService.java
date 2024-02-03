package com.mrxacx.spacefleet.service.impl;

import com.mrxacx.spacefleet.controller.dto.impl.JourneyDTO;
import com.mrxacx.spacefleet.exception.UnexpectedDBResponseException;
import com.mrxacx.spacefleet.model.CrewMember;
import com.mrxacx.spacefleet.model.Journey;
import com.mrxacx.spacefleet.model.Spaceship;
import com.mrxacx.spacefleet.repository.ICrewMemberRepository;
import com.mrxacx.spacefleet.repository.IJourneyRepository;
import com.mrxacx.spacefleet.service.IJourneyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class JourneyService implements IJourneyService {
  
  final private SpaceshipService spaceshipService;
  final private IJourneyRepository journeyRepository;
  final private ICrewMemberRepository crewMemberRepository;
  
  @Override
  public Journey record(JourneyDTO journeyDTO) {
    
    final Spaceship spaceship = spaceshipService.fetch(journeyDTO.getSpaceshipId()); // fetch spaceship
    final List<CrewMember> crew = journeyDTO
        .getCrew()
        .stream()
        .map((crewMemberId) -> crewMemberRepository // fetch all crew
            .findById(crewMemberId)
            .orElseThrow(() ->
                new UnexpectedDBResponseException("Tripulante desconhecido.") // throw if any crew member is not found
            ))
        .toList();
    
    return journeyRepository.save(
        Journey
            .builder()
            .spaceship(spaceship)
            .goal(journeyDTO.getGoal())
            .crew(crew)
            .build()
    );
  }
  
  @Override
  public Journey fetch(UUID journeyId) {
    return journeyRepository
        .findById(journeyId)
        .orElseThrow(() -> new UnexpectedDBResponseException(
            String.format("A missão %s não foi encontrada", journeyId.toString())
        ));
  }
  
  @Override
  public Journey finish(UUID journeyId) {
    Journey journey = fetch(journeyId);
    
    if (journey.getDurationInMonths() != null) {
      throw new RuntimeException(
          String.format("A missão %s já foi finalizada anteriormente", journeyId.toString())
      );
    }
    
    journey.setDurationInMonths(
        Period
            .between(journey.getDate(), LocalDate.now())
            .toTotalMonths()
    );
    
    return journeyRepository.save(journey);
  }
}
