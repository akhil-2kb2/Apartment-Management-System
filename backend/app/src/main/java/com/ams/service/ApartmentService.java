package com.ams.service;

import com.ams.dto.ApartmentDTO;
import com.ams.entity.Apartment;
import com.ams.repository.ApartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ApartmentService {

    private final ApartmentRepository apartmentRepository;

    public ApartmentService(ApartmentRepository apartmentRepository) {
        this.apartmentRepository = apartmentRepository;
    }

    public ApartmentDTO createApartment(ApartmentDTO dto) {
        Apartment apartment = new Apartment();
        apartment.setName(dto.getName());
        apartment.setAddress(dto.getAddress());
        apartment.setApartmentType(dto.getApartmentType());
        apartment.setFloorNumber(dto.getFloorNumber());
        apartment.setOccupied(false);
        apartment.setDeleted(false);
        apartmentRepository.save(apartment);
        dto.setId(apartment.getId());
        return dto;
    }

    public List<ApartmentDTO> getAllApartments() {
        return apartmentRepository.findByIsDeletedFalse()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ApartmentDTO getApartmentById(Long id) {
        Apartment apartment = apartmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Apartment not found with ID: " + id));
        return convertToDTO(apartment);
    }

    public String updateApartment(Long id, ApartmentDTO dto) {
        Optional<Apartment> optional = apartmentRepository.findById(id);
        if (optional.isEmpty()) {
            return "Apartment not found";
        }
    
        Apartment apartment = optional.get();
    
        if (apartment.isDeleted()) {
            return "Apartment has been deleted and cannot be updated";
        }
    
        apartment.setName(dto.getName());
        apartment.setAddress(dto.getAddress());
        apartment.setApartmentType(dto.getApartmentType());
        apartment.setFloorNumber(dto.getFloorNumber());
    
        apartmentRepository.save(apartment);
        return "Apartment updated successfully";
    }
    

    public String softDeleteApartment(Long id) {
        Optional<Apartment> optional = apartmentRepository.findById(id);
        if (optional.isEmpty()) return "Apartment not found";

        Apartment apartment = optional.get();
        apartment.setDeleted(true);
        apartmentRepository.save(apartment);
        return "Apartment deleted (soft) successfully";
    }

    public String toggleOccupied(Long id, boolean isOccupied) {
        Optional<Apartment> optional = apartmentRepository.findById(id);
        if (optional.isEmpty()) return "Apartment not found";

        Apartment apartment = optional.get();
        apartment.setOccupied(isOccupied);
        apartmentRepository.save(apartment);

        return isOccupied ? "Apartment marked as Occupied" : "Apartment marked as Vacant";
    }

    private ApartmentDTO convertToDTO(Apartment apartment) {
        ApartmentDTO dto = new ApartmentDTO();
        dto.setId(apartment.getId());
        dto.setName(apartment.getName());
        dto.setAddress(apartment.getAddress());
        dto.setApartmentType(apartment.getApartmentType());
        dto.setFloorNumber(apartment.getFloorNumber());
        dto.setOccupied(apartment.isOccupied());
        return dto;
    }
}
