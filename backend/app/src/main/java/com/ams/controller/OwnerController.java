package com.ams.controller;

import com.ams.dto.OwnerDTO;
import com.ams.service.OwnerService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/owners")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<OwnerDTO> createOwner(@RequestBody OwnerDTO dto) {
        return ResponseEntity.ok(ownerService.createOwner(dto));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'SECRETARY')")
    @GetMapping
    public ResponseEntity<List<OwnerDTO>> getAllOwners() {
        return ResponseEntity.ok(ownerService.getAllOwners());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateOwner(@PathVariable Long id, @RequestBody OwnerDTO dto) {
        return ResponseEntity.ok(ownerService.updateOwner(id, dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOwner(@PathVariable Long id) {
        return ResponseEntity.ok(ownerService.softDeleteOwner(id));
    }
}
