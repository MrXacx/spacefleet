package com.mrxacx.spacefleet.resource.dto;

import lombok.*;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class RepairDTO {
    private String spaceshipId;
    private String fault;
}
