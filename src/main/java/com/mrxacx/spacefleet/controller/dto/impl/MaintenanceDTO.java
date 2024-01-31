package com.mrxacx.spacefleet.controller.dto.impl;

import com.mrxacx.spacefleet.controller.dto.IModelDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class MaintenanceDTO implements IModelDTO {
  private UUID id;
  private UUID spaceshipId;
  private Float correctionPercentage;
}
