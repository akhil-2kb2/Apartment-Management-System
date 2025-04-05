package com.ams.service;

import com.ams.dto.EntryExitLogDTO;
import com.ams.entity.*;
import com.ams.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import java.util.List;
//import java.time.LocalDateTime;
import java.util.Map;


@Service
public class EntryExitService {

    private final EntryExitLogRepository logRepo;
    private final VisitorRepository visitorRepo;
    private final VehicleRepository vehicleRepo;
    private final DriverRepository driverRepo;

    public EntryExitService(
            EntryExitLogRepository logRepo,
            VisitorRepository visitorRepo,
            VehicleRepository vehicleRepo,
            DriverRepository driverRepo
    ) {
        this.logRepo = logRepo;
        this.visitorRepo = visitorRepo;
        this.vehicleRepo = vehicleRepo;
        this.driverRepo = driverRepo;
    }

    public EntryExitLogDTO createEntryLog(EntryExitLogDTO dto) {
        EntryExitLog log = new EntryExitLog();
        log.setEntryTime(dto.getEntryTime());
        log.setExitTime(dto.getExitTime());
        log.setPersonCount(dto.getPersonCount() != null ? dto.getPersonCount() : 1);

        if (dto.getVisitorId() != null) {
            Visitor visitor = visitorRepo.findById(dto.getVisitorId())
                .orElseThrow(() -> new RuntimeException("Visitor with ID " + dto.getVisitorId() + " not found"));
            log.setVisitor(visitor);
        }
        
        if (dto.getVehicleId() != null) {
            Vehicle vehicle = vehicleRepo.findById(dto.getVehicleId())
                    .orElseThrow(() -> new RuntimeException("Vehicle not found"));
            log.setVehicle(vehicle);
        }

        if (dto.getDriverId() != null) {
            Driver driver = driverRepo.findById(dto.getDriverId())
                    .orElseThrow(() -> new RuntimeException("Driver not found"));
            log.setDriver(driver);
        }

        logRepo.save(log);
        dto.setId(log.getId());
        return dto;
    }

    public List<EntryExitLogDTO> getAllLogs(int page, int size) {
        Page<EntryExitLog> logs = logRepo.findByIsActiveTrue(PageRequest.of(page, size));
        return logs.getContent().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public String softDeleteLog(Long id) {
        Optional<EntryExitLog> optional = logRepo.findById(id);
        if (optional.isEmpty()) return "Entry not found";

        EntryExitLog log = optional.get();
        log.setActive(false);
        logRepo.save(log);
        return "Entry marked as inactive.";
    }

    private EntryExitLogDTO toDTO(EntryExitLog log) {
        EntryExitLogDTO dto = new EntryExitLogDTO();
        dto.setId(log.getId());
        dto.setEntryTime(log.getEntryTime());
        dto.setExitTime(log.getExitTime());
        dto.setPersonCount(log.getPersonCount());

        if (log.getVisitor() != null) dto.setVisitorId(log.getVisitor().getId());
        if (log.getVehicle() != null) dto.setVehicleId(log.getVehicle().getId());
        if (log.getDriver() != null) dto.setDriverId(log.getDriver().getId());

        return dto;
    }
    public String recordExitTime(Long id, Map<String, Object> payload) {
    EntryExitLog log = logRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Log not found"));

    if (payload.get("exitTime") == null) {
        throw new RuntimeException("exitTime is required");
    }

    LocalDateTime exitTime = LocalDateTime.parse(payload.get("exitTime").toString());
    log.setExitTime(exitTime);

    if (payload.get("personCount") != null) {
        log.setPersonCount((Integer) payload.get("personCount"));
    }

    logRepo.save(log);
    return "Exit time recorded successfully";
}

}
