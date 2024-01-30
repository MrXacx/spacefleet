package com.mrxacx.spacefleet.model;

import jakarta.persistence.*;
import jdk.jfr.Unsigned;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@Builder
@Data
@Entity
@NoArgsConstructor
public class Maintenance implements IModel {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  protected UUID id;
  @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Spaceship spaceship;
  @Unsigned
  private Float correctionPercentage;
}
