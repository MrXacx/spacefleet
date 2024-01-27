package com.mrxacx.spacefleet.model;

import jakarta.persistence.*;
import jdk.jfr.Unsigned;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
@Entity
@NoArgsConstructor
public class Spaceship {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String model;
    private String name;
    @Unsigned private Long cost;
    private Double length;
    @Unsigned  private Long crew;
    @Unsigned private  Long passengers;
    @Unsigned private Double cargoCapacity;
    @Unsigned private Float hyperdriveRating;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Journey> journeys;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Maintenance> maintenances;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Repair> repairs;
}