package com.mrxacx.spacefleet.controller;

import com.mrxacx.spacefleet.controller.dto.impl.CrewMemberDTO;
import com.mrxacx.spacefleet.model.CrewMember;
import com.mrxacx.spacefleet.service.impl.CrewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/crew")
public class CrewController {
  final private CrewService crewService;
  
  @PostMapping
  public CrewMember recordCrewMember(@RequestBody CrewMemberDTO crewMemberDTO) {
    return crewService.record(crewMemberDTO);
  }
  
  @GetMapping("/{id}")
  public CrewMember fetchCrewMember(@PathVariable("id") UUID crewMemberId) {
    return crewService
        .fetch(crewMemberId);
  }
  
  @GetMapping
  public List<CrewMember> fetchCrew() {
    return crewService.fetchList();
  }
  
  @DeleteMapping("/{id}")
  public void deleteCrewMember(@PathVariable("id") UUID crewMemberId) {
    crewService.delete(crewMemberId);
  }
}
