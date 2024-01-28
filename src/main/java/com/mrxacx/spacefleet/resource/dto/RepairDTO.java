package com.mrxacx.spacefleet.dtos;

import lombok.*;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class RepairDTO {
    private String spaceshipId;
    private String fault;
}
