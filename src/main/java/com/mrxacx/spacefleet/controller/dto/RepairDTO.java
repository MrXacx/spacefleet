package com.mrxacx.spacefleet.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class RepairDTO implements IModelDTO{
  private UUID spaceshipId;
  private String fault;
}
