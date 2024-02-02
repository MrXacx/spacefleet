package com.mrxacx.spacefleet.controller.dto.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mrxacx.spacefleet.controller.dto.IModelDTO;
import com.mrxacx.spacefleet.controller.dto.ParserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpaceshipDTO extends ParserDTO implements IModelDTO {
  private String model;
  private String name;
  private String manufacturer;
  private Long cost_in_credits;
  private Double length;
  private Long crew;
  private Long passengers;
  private Double cargo_capacity;
  private Float hyperdrive_rating;
  
  public void setCrew(String crew) {
    this.crew = parseStringAttributeToLong(Arrays.stream(crew.split("/(-)/")).toList().getLast());
  }
  
  public void setPassengers(String passengers) {
    this.passengers = passengers.equals("n/a") ? 0 : parseStringAttributeToLong(passengers);
  }
  
  public void setLength(String length) {
    this.length = parseStringAttributeToDouble(length);
  }
  
  public void setCargo_capacity(String cargoCapacity) {
    this.cargo_capacity = parseStringAttributeToDouble(cargoCapacity);
  }
}
