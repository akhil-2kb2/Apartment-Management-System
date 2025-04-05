package com.ams.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EntryExitLogDTO {
    private Long id;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private Long visitorId;
    private Long vehicleId;
    private Long driverId;
    private Integer personCount;
}
