package com.mrxacx.spacefleet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Data
@Entity
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Journey implements IModel {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  protected UUID id;
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JsonIgnore
  private Spaceship spaceship;
  @Builder.Default
  private LocalDate date = LocalDate.now();
  private Long durationInMonths;
  @ManyToMany
  @Builder.Default
  private List<CrewMember> crew = new ArrayList<>();
  private String goal;
}