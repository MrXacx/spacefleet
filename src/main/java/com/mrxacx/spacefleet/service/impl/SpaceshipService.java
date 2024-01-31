package com.mrxacx.spacefleet.service.impl;

import com.mrxacx.spacefleet.controller.dto.impl.MaintenanceDTO;
import com.mrxacx.spacefleet.controller.dto.impl.RepairDTO;
import com.mrxacx.spacefleet.controller.dto.impl.SpaceshipDTO;
import com.mrxacx.spacefleet.exception.UnexpectedDBResponseException;
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

/**
 * @author ariel
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class SpaceshipService implements ISpaceshipService {
  
  final private ISpaceshipRepository spaceshipRepository;
  final private IMaintenanceRepository maintenanceRepository;
  final private IRepairRepository repairRepository;
  
  @Override
  public Spaceship recordSpaceship(String model) {
    final Spaceship spaceship = new SWAPIClientService().fetchSpaceship(model);
    return spaceshipRepository.save(spaceship);
  }
  
  @Override
  public Spaceship fetchSpaceship(UUID spaceshipId) {
    return spaceshipRepository
        .findById(spaceshipId)
        .orElseThrow();
  }
  
  @Override
  public List<Spaceship> fetchSpaceshipsForName(String name) {
    return spaceshipRepository.findByName(name);
  }
  
  @Override
  public List<Spaceship> fetchSpaceshipsForModel(String model) {
    return spaceshipRepository.findByModel(model);
  }
  
  @Override
  public List<Spaceship> fetchSpaceshipsForManufacturer(String manufacturer) {
    return spaceshipRepository.findByManufacturer(manufacturer);
  }
  
  /**
   * @param spaceshipId
   * @param spaceshipDTO
   * @return
   */
  @Override
  public Spaceship updateSpaceship(UUID spaceshipId, SpaceshipDTO spaceshipDTO) {
    Spaceship spaceship = fetchSpaceship(spaceshipId);
    
    spaceship.setCrew(spaceshipDTO.getCrew());
    spaceship.setLength(spaceshipDTO.getLength());
    spaceship.setHyperdriveRating(spaceshipDTO.getHyperdrive_rating());
    spaceship.setCargoCapacity(spaceshipDTO.getCargo_capacity());
    spaceship.setCost(spaceshipDTO.getCost_in_credits());
    
    return spaceshipRepository.save(spaceship);
  }
  
  /**
   * @param spaceshipId
   */
  @Override
  public void removeSpaceship(UUID spaceshipId) {
    spaceshipRepository.delete(
        fetchSpaceship(spaceshipId)
    );
  }
  
  @Override
  public Repair recordSpaceshipRepair(RepairDTO repairDTO) {
    return repairRepository
        .save(
            Repair.builder()
                .spaceship(
                    fetchSpaceship(
                        repairDTO.getSpaceshipId()
                    )
                )
                .fault(repairDTO.getFault())
                .build()
        );
  }
  
  @Override
  public Repair fetchSpaceshipRepair(UUID repairId) {
    return repairRepository
        .findById(repairId)
        .orElseThrow(() -> new UnexpectedDBResponseException("Espaçonave não localizada."));
  }
  
  @Override
  public Repair finishSpaceshipRepair(UUID repairId) {
    Repair repair = fetchSpaceshipRepair(repairId);
    if (repair.isFinished()) {
      throw new RuntimeException(
          String.format("O reparo %s já foi finalizado.", repairId.toString())
      );
    }
    
    repair.setFinished(true);
    return repairRepository.save(repair);
  }
  
  @Override
  public Maintenance recordSpaceshipMaintenance(MaintenanceDTO maintenanceDTO) {
    return maintenanceRepository
        .save(
            Maintenance
                .builder()
                .spaceship(
                    fetchSpaceship(
                        maintenanceDTO.getSpaceshipId()
                    )
                )
                .build()
        );
  }
}
