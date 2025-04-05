package com.ams.service;
import com.ams.dto.DriverDTO;
import com.ams.entity.Driver; 
import com.ams.repository.DriverRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DriverService {

    private final DriverRepository driverRepo;

    public DriverService(DriverRepository driverRepo) {
        this.driverRepo = driverRepo;
    }

    public DriverDTO createDriver(DriverDTO dto) {
        Driver driver = new Driver();
        driver.setName(dto.getName());
        driver.setIdProofType(dto.getIdProofType());
        driver.setIdProofNumber(dto.getIdProofNumber());
        driver.setContact(dto.getContact());
        driverRepo.save(driver);
        dto.setId(driver.getId());
        return dto;
    }

    public List<DriverDTO> getAllDrivers() {
        return driverRepo.findByIsDeletedFalse().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public String softDeleteDriver(Long id) {
        Optional<Driver> optional = driverRepo.findById(id);
        if (optional.isEmpty()) return "Driver not found";
        Driver driver = optional.get();
        driver.setDeleted(true); 
        driverRepo.save(driver);
        return "Driver deleted (soft) successfully";
    }

    public String updateDriver(Long id, DriverDTO dto) {
        Optional<Driver> optional = driverRepo.findById(id);
        if (optional.isEmpty()) return "Driver not found";
        Driver driver = optional.get();
        driver.setName(dto.getName());
        driver.setIdProofType(dto.getIdProofType());
        driver.setIdProofNumber(dto.getIdProofNumber());
        driver.setContact(dto.getContact());
        driverRepo.save(driver);
        return "Driver updated successfully";
    }

    private DriverDTO toDTO(Driver driver) {
        return new DriverDTO(
                driver.getId(),
                driver.getName(),
                driver.getIdProofType(),
                driver.getIdProofNumber(),
                driver.getContact()
        );
    }
}
