package com.mrxacx.spacefleet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Data
@Entity
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Repair implements IModel {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  protected UUID id;
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JsonIgnore
  private Spaceship spaceship;
  private String fault;
  @Builder.Default
  private Date date = new Date();
  @Builder.Default
  private boolean finished = false;
}
