package com.mrxacx.spacefleet.controller.dto.impl;

import com.mrxacx.spacefleet.controller.dto.IModelDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class RepairDTO implements IModelDTO {
  private UUID id;
  private UUID spaceshipId;
  private List<String> faults;
  
  public String getFaults(){ return String.join(", ", faults);}
}
