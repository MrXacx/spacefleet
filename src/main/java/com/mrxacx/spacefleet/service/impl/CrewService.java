package com.mrxacx.spacefleet.service.impl;

import com.mrxacx.spacefleet.controller.dto.impl.CrewMemberDTO;
import com.mrxacx.spacefleet.exception.UnexpectedDBResponseException;
import com.mrxacx.spacefleet.model.CrewMember;
import com.mrxacx.spacefleet.repository.ICrewMemberRepository;
import com.mrxacx.spacefleet.service.ICrewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CrewService implements ICrewService {
  final private ICrewMemberRepository crewMemberRepository;
  
  @Override
  public CrewMember record(CrewMemberDTO crewMemberDTO) {
    return crewMemberRepository
        .save(
            CrewMember
                .builder()
                .name(crewMemberDTO.getName())
                .build()
        );
  }
  
  @Override
  public CrewMember fetch(UUID id){
    return crewMemberRepository
        .findById(id)
        .orElseThrow(() -> new UnexpectedDBResponseException(
            String.format("O tripulante %s não foi encontrado", id.toString())
        ));
  }
  
  @Override
  public List<CrewMember> fetchList() {
    return crewMemberRepository.findByOrderByNameAsc();
  }
  
  @Override
  public void delete(UUID id) { crewMemberRepository.delete(fetch(id)); }
}
