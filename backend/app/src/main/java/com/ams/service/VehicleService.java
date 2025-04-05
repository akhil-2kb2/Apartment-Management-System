package com.ams.service;

import com.ams.dto.VehicleDTO;
import com.ams.entity.Apartment;
import com.ams.entity.Vehicle;
import com.ams.repository.ApartmentRepository;
import com.ams.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepo;
    private final ApartmentRepository apartmentRepo;

    public VehicleService(VehicleRepository vehicleRepo, ApartmentRepository apartmentRepo) {
        this.vehicleRepo = vehicleRepo;
        this.apartmentRepo = apartmentRepo;
    }

    public VehicleDTO createVehicle(VehicleDTO dto) {
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleNumber(dto.getVehicleNumber());
        vehicle.setVehicleType(dto.getVehicleType());
        vehicle.setVehicleColor(dto.getVehicleColor());
        vehicle.setOwnerType(dto.getOwnerType());
        vehicle.setOwnerId(dto.getOwnerId());

        if (dto.getApartmentId() != null) {
            Apartment apt = apartmentRepo.findById(dto.getApartmentId())
                    .orElseThrow(() -> new RuntimeException("Apartment not found"));
            if (apt.isDeleted()) throw new RuntimeException("Apartment is deleted");
            vehicle.setApartment(apt);
        }

        vehicleRepo.save(vehicle);
        dto.setId(vehicle.getId());
        return dto;
    }

    public List<VehicleDTO> getAllVehicles() {
        return vehicleRepo.findByIsDeletedFalse().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public String updateVehicle(Long id, VehicleDTO dto) {
        Optional<Vehicle> optional = vehicleRepo.findById(id);
        if (optional.isEmpty()) return "Vehicle not found";

        Vehicle vehicle = optional.get();
        if (vehicle.isDeleted()) return "Vehicle has been deleted and cannot be updated";

        vehicle.setVehicleNumber(dto.getVehicleNumber());
        vehicle.setVehicleType(dto.getVehicleType());
        vehicle.setVehicleColor(dto.getVehicleColor());
        vehicle.setOwnerType(dto.getOwnerType());
        vehicle.setOwnerId(dto.getOwnerId());

        if (dto.getApartmentId() != null) {
            Apartment apt = apartmentRepo.findById(dto.getApartmentId())
                    .orElseThrow(() -> new RuntimeException("Apartment not found"));
            if (apt.isDeleted()) throw new RuntimeException("Cannot assign a deleted apartment");
            vehicle.setApartment(apt);
        } else {
            vehicle.setApartment(null);
        }

        vehicleRepo.save(vehicle);
        return "Vehicle updated successfully";
    }

    public String softDeleteVehicle(Long id) {
        Optional<Vehicle> optional = vehicleRepo.findById(id);
        if (optional.isEmpty()) return "Vehicle not found";

        Vehicle vehicle = optional.get();
        if (vehicle.isDeleted()) return "Vehicle already deleted";

        vehicle.setDeleted(true);
        vehicleRepo.save(vehicle);
        return "Vehicle archived successfully.";
    }

    private VehicleDTO toDTO(Vehicle v) {
        VehicleDTO dto = new VehicleDTO();
        dto.setId(v.getId());
        dto.setVehicleNumber(v.getVehicleNumber());
        dto.setVehicleType(v.getVehicleType());
        dto.setVehicleColor(v.getVehicleColor());
        dto.setOwnerType(v.getOwnerType());
        dto.setOwnerId(v.getOwnerId());
        if (v.getApartment() != null) {
            dto.setApartmentId(v.getApartment().getId());
        }
        return dto;
    }
}
