package com.ams.controller;
import com.ams.dto.ApartmentDTO;
import com.ams.service.ApartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/apartments")
public class ApartmentController {

    private final ApartmentService apartmentService;

    public ApartmentController(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    // ✅ Create a new apartment
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ApartmentDTO> createApartment(@RequestBody ApartmentDTO dto) {
        return ResponseEntity.ok(apartmentService.createApartment(dto));
    }

    // ✅ Get all apartments (non-deleted)
    @PreAuthorize("hasAnyRole('ADMIN', 'SECRETARY')")
    @GetMapping
    public ResponseEntity<List<ApartmentDTO>> getAllApartments() {
        return ResponseEntity.ok(apartmentService.getAllApartments());
    }

    // ✅ Get apartment by ID
    @PreAuthorize("hasAnyRole('ADMIN', 'SECRETARY')")
    @GetMapping("/{id}")
    public ResponseEntity<ApartmentDTO> getApartmentById(@PathVariable Long id) {
        return ResponseEntity.ok(apartmentService.getApartmentById(id));
    }

    // ✅ Update apartment details
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateApartment(@PathVariable Long id, @RequestBody ApartmentDTO dto) {
        return ResponseEntity.ok(apartmentService.updateApartment(id, dto));
    }

    // ✅ Soft delete apartment
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteApartment(@PathVariable Long id) {
        return ResponseEntity.ok(apartmentService.softDeleteApartment(id));
    }

    // ✅ Toggle occupancy status
    @PreAuthorize("hasAnyRole('ADMIN', 'SECRETARY')")
    @PatchMapping("/{id}/occupancy")
    public ResponseEntity<String> toggleOccupied(@PathVariable Long id, @RequestParam boolean occupied) {
        return ResponseEntity.ok(apartmentService.toggleOccupied(id, occupied));
    }
}
