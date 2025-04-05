package com.ams.service;

import com.ams.dto.TenantDTO;
import com.ams.entity.Apartment;
import com.ams.entity.Tenant;
import com.ams.repository.ApartmentRepository;
import com.ams.repository.TenantRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TenantService {

    private final TenantRepository tenantRepository;
    private final ApartmentRepository apartmentRepository;

    public TenantService(TenantRepository tenantRepository, ApartmentRepository apartmentRepository) {
        this.tenantRepository = tenantRepository;
        this.apartmentRepository = apartmentRepository;
    }

    public TenantDTO createTenant(TenantDTO dto) {
        Optional<Apartment> apartmentOpt = apartmentRepository.findById(dto.getApartmentId());
        if (apartmentOpt.isEmpty()) throw new RuntimeException("Apartment not found");

        Tenant tenant = new Tenant();
        tenant.setName(dto.getName());
        tenant.setEmail(dto.getEmail());
        tenant.setPhone(dto.getPhone());
        tenant.setApartment(apartmentOpt.get());
        tenant.setDeleted(false);

        tenant = tenantRepository.save(tenant);

        dto.setId(tenant.getId());
        return dto;
    }

    public List<TenantDTO> getAllTenants() {
        return tenantRepository.findByIsDeletedFalse()
                .stream()
                .map(t -> new TenantDTO(
                        t.getId(),
                        t.getName(),
                        t.getEmail(),
                        t.getPhone(),
                        t.getApartment().getId()
                ))
                .collect(Collectors.toList());
    }

    public String updateTenant(Long id, TenantDTO dto) {
        Optional<Tenant> optional = tenantRepository.findById(id);
        if (optional.isEmpty()) return "Tenant not found";

        Tenant tenant = optional.get();

        tenant.setName(dto.getName());
        tenant.setEmail(dto.getEmail());
        tenant.setPhone(dto.getPhone());

        if (!tenant.getApartment().getId().equals(dto.getApartmentId())) {
            Apartment newApartment = apartmentRepository.findById(dto.getApartmentId())
                    .orElseThrow(() -> new RuntimeException("Apartment not found"));
            tenant.setApartment(newApartment);
        }

        tenantRepository.save(tenant);
        return "Tenant updated successfully";
    }

    public String softDeleteTenant(Long id) {
        Optional<Tenant> optional = tenantRepository.findById(id);
        if (optional.isEmpty()) return "Tenant not found";

        Tenant tenant = optional.get();
        tenant.setDeleted(true);
        tenantRepository.save(tenant);
        return "Tenant soft deleted successfully";
    }
}
