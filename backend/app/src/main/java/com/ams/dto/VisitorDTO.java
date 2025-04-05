package com.ams.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class VisitorDTO {
    private Long id;
    private String name;
    private String phoneNumber;
    private Long apartmentId;
    private String idProofType;
    private String idProofNumber;
    private String purposeOfVisit;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    private Long vehicleId;
}
