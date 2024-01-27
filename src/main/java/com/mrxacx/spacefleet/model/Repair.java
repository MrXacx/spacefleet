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
public class Repair {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Spaceship spaceship;
    private String fault;
    @Builder.Default
    private Date date = new Date();
    @Builder.Default
    private boolean finished = false;
}
