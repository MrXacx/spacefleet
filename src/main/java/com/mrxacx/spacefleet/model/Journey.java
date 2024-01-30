package com.mrxacx.spacefleet.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Builder
@Data
@Entity
@NoArgsConstructor
public class Journey extends IModel {
  @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Spaceship spaceship;
  private Date date;
  private Date duration;
  @ManyToMany
  private List<CrewMember> crew;
  private String goal;
}