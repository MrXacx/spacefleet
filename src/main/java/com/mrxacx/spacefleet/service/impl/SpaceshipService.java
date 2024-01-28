package com.mrxacx.spacefleet.service.impl;

import com.mrxacx.spacefleet.resource.dto.RepairDTO;
import com.mrxacx.spacefleet.model.Maintenance;
import com.mrxacx.spacefleet.model.Repair;
import com.mrxacx.spacefleet.model.Spaceship;
import com.mrxacx.spacefleet.repository.IMaintenanceRepository;
import com.mrxacx.spacefleet.repository.IRepairRepository;
import com.mrxacx.spacefleet.repository.ISpaceshipRepository;
import com.mrxacx.spacefleet.service.ISpaceshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SpaceshipService implements ISpaceshipService {

    private ISpaceshipRepository spaceshipRepository;
    private IMaintenanceRepository maintenanceRepository;
    private IRepairRepository repairRepository;

    @Override
    public Spaceship recordSpaceship(String model) {
        return null;
    }

    @Override
    public Spaceship fetchSpaceship(String spaceshipId) {
        return spaceshipRepository
                .findById(UUID.fromString(spaceshipId))
                .orElseThrow();
    }

    @Override
    public List<Spaceship> fetchSpaceshipsForName(String name) {
        return spaceshipRepository
                .findAll((Spaceship sp) -> sp.getName().equals(name));
    }

    @Override
    public List<Spaceship> fetchSpaceshipsForModel(String model) {
        return spaceshipRepository
                .findAll((Spaceship sp) -> sp.getModel().equals(model));
    }

    @Override
    public List<Spaceship> fetchSpaceshipsForManufacter(String manufacter) {
        return spaceshipRepository
                .findAll((Spaceship sp) -> sp.getManufacter().equals(manufacter));
    }

    @Override
    public Repair recordSpaceshipRepair(RepairDTO repairDTO) {
        final Spaceship spaceship = retrieveSpaceship(repairDTO.getSpaceshipId());

        return repairRepository
                .save(
                        Repair.builder()
                                .spaceship(spaceship)
                                .fault(repairDTO.getFault())
                                .build()
                );
    }

    @Override
    public Repair fetchSpaceshipRepair(String repairId) {
        return repairRepository
                .findById(UUID.fromString(repairId))
                .orElseThrow();
    }

    @Override
    public Repair finishSpaceshipRepair(String repairId) {
        Repair repair = retrieveSpaceshipRepair(repairId);
        repair.setFinished(true);
        return repairRepository.save(repair);
    }

    @Override
    public Maintenance recordSpaceshipMaintenance(String spaceshipId) {
        final Spaceship spaceship = retrieveSpaceship(spaceshipId);

        return maintenanceRepository
                .save(
                        Maintenance
                                .builder()
                                .spaceship(spaceship)
                                .build()
                );
    }
}
