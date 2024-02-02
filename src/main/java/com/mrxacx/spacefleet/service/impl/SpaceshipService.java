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
import java.util.function.Consumer;

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
  public Spaceship record(String model) {
    final Spaceship spaceship = new SWAPIClientService().fetchSpaceship(model);
    return spaceshipRepository.save(spaceship);
  }
  
  @Override
  public Spaceship fetch(UUID spaceshipId) {
    return spaceshipRepository
        .findById(spaceshipId)
        .orElseThrow();
  }
  
  @Override
  public List<Spaceship> fetchForName(String name) {
    return spaceshipRepository.findByName(name);
  }
  
  @Override
  public List<Spaceship> fetchForModel(String model) {
    return spaceshipRepository.findByModel(model);
  }
  
  @Override
  public List<Spaceship> fetchForManufacturer(String manufacturer) {
    return spaceshipRepository.findByManufacturer(manufacturer);
  }
  
  private <T extends Number> void execIfPositive(Consumer<T> setterConsumer, T value) {
    if (value != null && value.doubleValue() > 0) {
      setterConsumer.accept(value);
    }
  }
  
  @Override
  public Spaceship update(UUID spaceshipId, SpaceshipDTO spaceshipDTO) {
    Spaceship spaceship = fetch(spaceshipId);
    execIfPositive(spaceship::setCrew, spaceshipDTO.getCrew());
    execIfPositive(spaceship::setLength, spaceshipDTO.getLength());
    execIfPositive(spaceship::setHyperdriveRating, spaceshipDTO.getHyperdrive_rating());
    execIfPositive(spaceship::setCargoCapacity, spaceshipDTO.getCargo_capacity());
    execIfPositive(spaceship::setCost, spaceshipDTO.getCost_in_credits());
    
    return spaceshipRepository.save(spaceship);
  }
  
  @Override
  public void remove(UUID spaceshipId) {
    spaceshipRepository.delete(fetch(spaceshipId));
  }
  
  @Override
  public Repair recordRepair(RepairDTO repairDTO) {
    final Spaceship spaceship = fetch(repairDTO.getSpaceshipId());
    final Repair repair = Repair
        .builder()
        .spaceship(spaceship)
        .fault(repairDTO.getFaults())
        .build();
    spaceship.getRepairs().add(repair);
    return repairRepository.save(repair);
  }
  
  @Override
  public Repair fetchRepair(UUID repairId) {
    return repairRepository
        .findById(repairId)
        .orElseThrow(() -> new UnexpectedDBResponseException("Reparo não localizado."));
  }
  
  @Override
  public Repair finishRepair(UUID repairId) {
    Repair repair = fetchRepair(repairId);
    if (repair.isFinished()) {
      throw new RuntimeException(
          String.format("O reparo %s já foi finalizado.", repairId.toString())
      );
    }
    
    repair.setFinished(true);
    return repairRepository.save(repair);
  }
  
  @Override
  public Maintenance recordMaintenance(MaintenanceDTO maintenanceDTO) {
    final Spaceship spaceship = fetch(maintenanceDTO.getSpaceshipId());
    final Maintenance maintenance = Maintenance
        .builder()
        .spaceship(spaceship)
        .correctionPercentage(maintenanceDTO.getCorrectionPercentage())
        .build();
    spaceship.getMaintenances().add(maintenance);
    return maintenanceRepository.save(maintenance);
  }
}
