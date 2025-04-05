package com.ams.controller;

import com.ams.dto.EntryExitLogDTO;
import com.ams.service.EntryExitService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
//import java.time.LocalDateTime;


@RestController
@RequestMapping("/api/entry-exit")
public class EntryExitController {

    private final EntryExitService entryExitService;

    public EntryExitController(EntryExitService entryExitService) {
        this.entryExitService = entryExitService;
    }

    @PreAuthorize("hasRole('SECURITY')")
    @PostMapping
    public ResponseEntity<EntryExitLogDTO> createEntryLog(@Valid @RequestBody EntryExitLogDTO dto){
        return ResponseEntity.ok(entryExitService.createEntryLog(dto));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'SECRETARY', 'SECURITY')")
    @GetMapping
    public ResponseEntity<List<EntryExitLogDTO>> getAllLogs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(entryExitService.getAllLogs(page, size));
    }

    @PreAuthorize("hasRole('SECURITY')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> softDelete(@PathVariable Long id) {
        return ResponseEntity.ok(entryExitService.softDeleteLog(id));
    }
    @PreAuthorize("hasRole('SECURITY')")
@PutMapping("/{id}/exit")
public ResponseEntity<String> recordExit(
    @PathVariable Long id,
    @RequestBody Map<String, Object> payload
) {
    return ResponseEntity.ok(entryExitService.recordExitTime(id, payload));
}

}
