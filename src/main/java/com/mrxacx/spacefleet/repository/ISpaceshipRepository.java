package com.mrxacx.spacefleet.repository;

import com.mrxacx.spacefleet.model.Spaceship;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ISpaceshipRepository extends JpaRepository<Spaceship, UUID> {}