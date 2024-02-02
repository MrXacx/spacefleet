package com.mrxacx.spacefleet.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jdk.jfr.Unsigned;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Data
@Entity
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Spaceship implements IModel {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  protected UUID id;
  private String model;
  private String name;
  private String manufacturer;
  @Unsigned
  private Long cost;
  private Double length;
  @Unsigned
  private Long crew;
  @Unsigned
  private Long passengers;
  @Unsigned
  private Double cargoCapacity;
  @Unsigned
  private Float hyperdriveRating;
  @OneToMany(cascade = CascadeType.ALL)
  @Builder.Default
  private List<Journey> journeys = new ArrayList<>();
  @OneToMany(cascade = CascadeType.ALL)
  @Builder.Default
  private List<Maintenance> maintenances = new ArrayList<>();
  @OneToMany(cascade = CascadeType.ALL)
  @Builder.Default
  private List<Repair> repairs = new ArrayList<>();
}