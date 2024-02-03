package com.mrxacx.spacefleet.controller.dto.impl;

import com.mrxacx.spacefleet.controller.dto.IModelDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CrewMemberDTO implements IModelDTO {
  private String name;
}
