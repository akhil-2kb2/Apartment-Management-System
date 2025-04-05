package com.ams.controller;

import com.ams.dto.VehicleDTO;
import com.ams.service.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'SECRETARY', 'SECURITY')")
    @PostMapping
    public ResponseEntity<VehicleDTO> addVehicle(@RequestBody VehicleDTO dto) {
        return ResponseEntity.ok(vehicleService.createVehicle(dto));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'SECRETARY', 'SECURITY')")
    @GetMapping
    public ResponseEntity<List<VehicleDTO>> getVehicles() {
        return ResponseEntity.ok(vehicleService.getAllVehicles());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'SECRETARY')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable Long id) {
        return ResponseEntity.ok(vehicleService.softDeleteVehicle(id));
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'SECRETARY')")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateVehicle(@PathVariable Long id, @RequestBody VehicleDTO dto) {
        return ResponseEntity.ok(vehicleService.updateVehicle(id, dto));
    }

}
