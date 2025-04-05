package com.ams.controller;

import com.ams.dto.TenantDTO;
import com.ams.service.TenantService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tenants")
public class TenantController {

    private final TenantService tenantService;

    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_SECRETARY')")
    @PostMapping
    public ResponseEntity<TenantDTO> createTenant(@RequestBody TenantDTO dto) {
        return ResponseEntity.ok(tenantService.createTenant(dto));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_SECRETARY')")
    @GetMapping
    public ResponseEntity<List<TenantDTO>> getAllTenants() {
        return ResponseEntity.ok(tenantService.getAllTenants());
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_SECRETARY')")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateTenant(@PathVariable Long id, @RequestBody TenantDTO dto) {
        return ResponseEntity.ok(tenantService.updateTenant(id, dto));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_SECRETARY')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> softDeleteTenant(@PathVariable Long id) {
        return ResponseEntity.ok(tenantService.softDeleteTenant(id));
    }
}
