package com.mrxacx.spacefleet.service;

import com.mrxacx.spacefleet.controller.dto.impl.CrewMemberDTO;
import com.mrxacx.spacefleet.model.CrewMember;

import java.util.List;
import java.util.UUID;

public interface ICrewService {
  
  CrewMember record(CrewMemberDTO crewMemberDTO);
  CrewMember fetch(UUID id);
  List<CrewMember> fetchList();
  void delete(UUID id);
}
