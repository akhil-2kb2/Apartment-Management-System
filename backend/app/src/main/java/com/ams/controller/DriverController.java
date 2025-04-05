package com.ams.controller;

import com.ams.dto.DriverDTO;
import com.ams.service.DriverService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    // ✅ Create Driver — SECURITY can do this
    @PreAuthorize("hasAnyRole('ADMIN', 'SECRETARY', 'SECURITY')")
    @PostMapping
    public ResponseEntity<DriverDTO> createDriver(@RequestBody DriverDTO dto) {
        return ResponseEntity.ok(driverService.createDriver(dto));
    }

    // ✅ View All Drivers — SECURITY can view
    @PreAuthorize("hasAnyRole('ADMIN', 'SECRETARY', 'SECURITY')")
    @GetMapping
    public ResponseEntity<List<DriverDTO>> getAllDrivers() {
        return ResponseEntity.ok(driverService.getAllDrivers());
    }

    // ✅ Update Driver — SECURITY can update
    @PreAuthorize("hasAnyRole('ADMIN', 'SECRETARY', 'SECURITY')")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateDriver(@PathVariable Long id, @RequestBody DriverDTO dto) {
        return ResponseEntity.ok(driverService.updateDriver(id, dto));
    }

    // ✅ Soft Delete Driver — SECURITY can archive
    @PreAuthorize("hasAnyRole('ADMIN', 'SECRETARY', 'SECURITY')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> softDeleteDriver(@PathVariable Long id) {
        return ResponseEntity.ok(driverService.softDeleteDriver(id));
    }
}
