package com.ams.dto;

import lombok.Data;

@Data
public class VehicleDTO {
    private Long id;
    private String vehicleNumber;
    private String vehicleType;
    private String vehicleColor;
    private String ownerType;    // "OWNER", "TENANT", "VISITOR"
    private Long ownerId;        // ID of the respective owner
    private Long apartmentId;    // optional
}
