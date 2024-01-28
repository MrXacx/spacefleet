package com.mrxacx.spacefleet.repository;

import com.mrxacx.spacefleet.model.CrewMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICrewMember extends JpaRepository<CrewMember, String> {}