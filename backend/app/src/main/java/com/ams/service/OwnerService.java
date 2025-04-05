package com.ams.service;

import com.ams.dto.OwnerDTO;
import com.ams.entity.Apartment;
import com.ams.entity.FamilyMember;
import com.ams.entity.Owner;
import com.ams.enums.ResidencyStatus;
import com.ams.repository.ApartmentRepository;
import com.ams.repository.FamilyMemberRepository;
import com.ams.repository.OwnerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OwnerService {

    private final OwnerRepository ownerRepo;
    private final ApartmentRepository apartmentRepo;
    private final FamilyMemberRepository familyRepo;

    public OwnerService(OwnerRepository ownerRepo, ApartmentRepository apartmentRepo, FamilyMemberRepository familyRepo) {
        this.ownerRepo = ownerRepo;
        this.apartmentRepo = apartmentRepo;
        this.familyRepo = familyRepo;
    }

    public OwnerDTO createOwner(OwnerDTO dto) {
        Owner tempOwner = new Owner();
        tempOwner.setName(dto.getName());
        tempOwner.setEmail(dto.getEmail());
        tempOwner.setPhone(dto.getPhone());
        tempOwner.setDeleted(false);

        if (dto.getApartmentIds() != null) {
            List<Apartment> apartments = apartmentRepo.findAllById(dto.getApartmentIds());
            tempOwner.setApartments(apartments);
        }

        Owner savedOwner = ownerRepo.save(tempOwner);

        if (savedOwner.getApartments() != null) {
            savedOwner.getApartments().forEach(a -> a.setOwner(savedOwner));
            apartmentRepo.saveAll(savedOwner.getApartments());
        }

        // 🔁 Auto-create "Self" FamilyMember if none exist
        long familyCount = familyRepo.countByOwnerIdAndIsDeletedFalse(savedOwner.getId());
        if (familyCount == 0) {
            FamilyMember member = new FamilyMember();
            member.setName(savedOwner.getName());
            member.setRelation("Self");
            member.setOwner(savedOwner);
            member.setResidencyStatus(ResidencyStatus.RESIDENT);
            member.setDeleted(false);
            familyRepo.save(member);
        }

        dto.setId(savedOwner.getId());
        return dto;
    }

    public List<OwnerDTO> getAllOwners() {
        return ownerRepo.findByIsDeletedFalse()
                .stream()
                .map(o -> new OwnerDTO(
                        o.getId(),
                        o.getName(),
                        o.getEmail(),
                        o.getPhone(),
                        o.getApartments().stream()
                                .map(Apartment::getId)
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }

    public String updateOwner(Long id, OwnerDTO dto) {
        Optional<Owner> optional = ownerRepo.findById(id);
        if (optional.isEmpty()) return "Owner not found";
    
        Owner owner = optional.get();
        if (owner.isDeleted()) return "Owner has been deleted and cannot be updated";
    
        owner.setName(dto.getName());
        owner.setEmail(dto.getEmail());
        owner.setPhone(dto.getPhone());
    
        if (dto.getApartmentIds() != null) {
            List<Apartment> apartments = apartmentRepo.findAllById(dto.getApartmentIds());
            apartments.forEach(a -> a.setOwner(owner));
            owner.setApartments(apartments);
        }
    
        ownerRepo.save(owner);
        return "Owner updated successfully";
    }

    public String softDeleteOwner(Long id) {
        Optional<Owner> optional = ownerRepo.findById(id);
        if (optional.isEmpty()) return "Owner not found";

        Owner owner = optional.get();
        owner.setDeleted(true);
        ownerRepo.save(owner);
        return "Owner deleted (soft) successfully";
    }
}
