package com.ams.service;

import com.ams.dto.VisitorDTO;
import com.ams.entity.Apartment;
import com.ams.entity.Vehicle;
import com.ams.entity.Visitor;
import com.ams.repository.ApartmentRepository;
import com.ams.repository.VehicleRepository;
import com.ams.repository.VisitorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VisitorService {

    private final VisitorRepository visitorRepo;
    private final ApartmentRepository apartmentRepo;
    private final VehicleRepository vehicleRepo;

    public VisitorService(VisitorRepository visitorRepo, ApartmentRepository apartmentRepo, VehicleRepository vehicleRepo) {
        this.visitorRepo = visitorRepo;
        this.apartmentRepo = apartmentRepo;
        this.vehicleRepo = vehicleRepo;
    }

    public VisitorDTO createVisitor(VisitorDTO dto) {
        Visitor visitor = new Visitor();
        visitor.setName(dto.getName());
        visitor.setPhoneNumber(dto.getPhoneNumber());
        visitor.setPurposeOfVisit(dto.getPurposeOfVisit());
        visitor.setIdProofType(dto.getIdProofType());
        visitor.setIdProofNumber(dto.getIdProofNumber());
        visitor.setCheckInTime(dto.getCheckInTime());
        visitor.setCheckOutTime(dto.getCheckOutTime());

        Apartment apt = apartmentRepo.findById(dto.getApartmentId())
                .orElseThrow(() -> new RuntimeException("Apartment not found"));
        visitor.setApartment(apt);

        if (dto.getVehicleId() != null) {
            Vehicle vehicle = vehicleRepo.findById(dto.getVehicleId())
                    .orElseThrow(() -> new RuntimeException("Vehicle not found"));
            visitor.setVehicle(vehicle);
        }

        visitorRepo.save(visitor);
        dto.setId(visitor.getId());
        return dto;
    }

    public List<VisitorDTO> getAllVisitors() {
        return visitorRepo.findByIsDeletedFalse().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public String updateVisitor(Long id, VisitorDTO dto) {
        Optional<Visitor> optional = visitorRepo.findById(id);
        if (optional.isEmpty()) {
            return "Visitor not found";
        }

        Visitor visitor = optional.get();

        if (visitor.isDeleted()) {
            return "Visitor has been deleted and cannot be updated";
        }

        visitor.setName(dto.getName());
        visitor.setPhoneNumber(dto.getPhoneNumber());
        visitor.setPurposeOfVisit(dto.getPurposeOfVisit());
        visitor.setIdProofType(dto.getIdProofType());
        visitor.setIdProofNumber(dto.getIdProofNumber());
        visitor.setCheckInTime(dto.getCheckInTime());
        visitor.setCheckOutTime(dto.getCheckOutTime());

        if (dto.getApartmentId() != null) {
            Apartment apt = apartmentRepo.findById(dto.getApartmentId())
                    .orElseThrow(() -> new RuntimeException("Apartment not found"));
            visitor.setApartment(apt);
        }

        if (dto.getVehicleId() != null) {
            Vehicle vehicle = vehicleRepo.findById(dto.getVehicleId())
                    .orElseThrow(() -> new RuntimeException("Vehicle not found"));
            visitor.setVehicle(vehicle);
        } else {
            visitor.setVehicle(null); // Remove vehicle link if null
        }

        visitorRepo.save(visitor);
        return "Visitor updated successfully";
    }

    public String deleteVisitor(Long id) {
        Optional<Visitor> optional = visitorRepo.findById(id);
        if (optional.isEmpty()) return "Visitor not found";

        Visitor visitor = optional.get();

        if (visitor.isDeleted()) {
            return "Visitor already deleted";
        }

        visitor.setDeleted(true);
        visitorRepo.save(visitor);
        return "Visitor archived successfully.";
    }

    private VisitorDTO toDTO(Visitor v) {
        VisitorDTO dto = new VisitorDTO();
        dto.setId(v.getId());
        dto.setName(v.getName());
        dto.setPhoneNumber(v.getPhoneNumber());
        dto.setApartmentId(v.getApartment().getId());
        dto.setIdProofType(v.getIdProofType());
        dto.setIdProofNumber(v.getIdProofNumber());
        dto.setPurposeOfVisit(v.getPurposeOfVisit());
        dto.setCheckInTime(v.getCheckInTime());
        dto.setCheckOutTime(v.getCheckOutTime());
        if (v.getVehicle() != null) {
            dto.setVehicleId(v.getVehicle().getId());
        }
        return dto;
    }
}
