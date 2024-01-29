package com.mrxacx.spacefleet.model;

import jakarta.persistence.*;
import jdk.jfr.Unsigned;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Data
@Entity
@NoArgsConstructor
public class Spaceship extends IModel{
    private String model;
    private String name;
    private String manufacter;
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