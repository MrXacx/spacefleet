package com.mrxacx.spacefleet.repository;

import com.mrxacx.spacefleet.model.CrewMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ICrewMemberRepository extends JpaRepository<CrewMember, UUID> {
  List<CrewMember> findByOrderByNameAsc();
}