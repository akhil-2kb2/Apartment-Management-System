package com.ams.service;

import com.ams.dto.FamilyMemberDTO;
import com.ams.entity.Apartment;
import com.ams.entity.FamilyMember;
import com.ams.repository.ApartmentRepository;
import com.ams.repository.FamilyMemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FamilyMemberService {

    private final FamilyMemberRepository familyMemberRepo;
    private final ApartmentRepository apartmentRepo;

    public FamilyMemberService(FamilyMemberRepository familyMemberRepo, ApartmentRepository apartmentRepo) {
        this.familyMemberRepo = familyMemberRepo;
        this.apartmentRepo = apartmentRepo;
    }

    public List<FamilyMemberDTO> getAllFamilyMembers() {
        return familyMemberRepo.findAll()
                .stream()
                .filter(fm -> !fm.isDeleted())
                .map(fm -> new FamilyMemberDTO(
                        fm.getId(),
                        fm.getName(),
                        fm.getRelation(),
                        fm.getApartment().getId(),
                        fm.getAge(),
                        fm.getContact(),
                        fm.getIdProofType(),
                        fm.getIdProofNumber(),
                        fm.getResidencyStatus().toString(),
                        fm.getOwner() != null ? fm.getOwner().getId() : null,
                        fm.getTenant() != null ? fm.getTenant().getId() : null
                ))
                .collect(Collectors.toList());
    }

    // 🔧 Future methods like:
    // public FamilyMemberDTO createFamilyMember(FamilyMemberDTO dto) {
    //     Apartment apartment = apartmentRepo.findById(dto.getApartmentId())
    //         .orElseThrow(() -> new RuntimeException("Apartment not found"));
    //     ...
    // }

}
