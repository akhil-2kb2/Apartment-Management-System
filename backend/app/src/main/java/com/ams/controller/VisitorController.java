package com.ams.controller;

import com.ams.dto.VisitorDTO;
import com.ams.service.VisitorService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visitors")
public class VisitorController {

    private final VisitorService visitorService;

    public VisitorController(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'SECRETARY', 'SECURITY')")
    @PostMapping
    public ResponseEntity<VisitorDTO> addVisitor(@RequestBody VisitorDTO dto) {
        return ResponseEntity.ok(visitorService.createVisitor(dto));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'SECRETARY', 'SECURITY')")
    @GetMapping
    public ResponseEntity<List<VisitorDTO>> getVisitors() {
        return ResponseEntity.ok(visitorService.getAllVisitors());
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'SECRETARY')")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateVisitor(@PathVariable Long id, @RequestBody VisitorDTO dto) {
    return ResponseEntity.ok(visitorService.updateVisitor(id, dto));
}


    @PreAuthorize("hasAnyRole('ADMIN', 'SECRETARY')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> archiveVisitor(@PathVariable Long id) {
        return ResponseEntity.ok(visitorService.deleteVisitor(id));
    }
}
