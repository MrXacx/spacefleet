package com.mrxacx.spacefleet.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/*
@AllArgsConstructor
@Data
@NoArgsConstructor
public class SpaceshipDTO {
  private String model;
}*/

@AllArgsConstructor
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpaceshipDTO implements IModelDTO {
  private String model;
  private String name;
  private String manufacturer;
  private Long cost_in_credits;
  private Double length;
  private Long crew;
  private Long passengers;
  private Double cargo_capacity;
  private Float hyperdrive_rating;
  
  public void setCrew(String crew){
    this.crew = Long.parseLong(crew.replace(",", ""));
  }
  public void setPassengers(String passengers){
    this.passengers = Long.parseLong(passengers.replace(",", ""));
  }
}
