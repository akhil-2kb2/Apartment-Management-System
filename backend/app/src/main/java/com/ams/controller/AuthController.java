package com.ams.controller;
import com.ams.dto.AuthDTO;
import com.ams.service.UserService;
import com.ams.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    public AuthController(
            AuthenticationManager authenticationManager,
            UserDetailsService userDetailsService,
            JwtUtil jwtUtil,
            UserService userService
    ) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @PostMapping("/register-admin")
    public ResponseEntity<String> registerAdmin(@RequestBody AuthDTO authDTO) {
        authDTO.setRoles(List.of("ROLE_ADMIN"));
        String result = userService.registerUser(authDTO);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/register-secretary")
    public ResponseEntity<String> registerSecretary(@RequestBody AuthDTO authDTO) {
        authDTO.setRoles(List.of("ROLE_SECRETARY"));
        String result = userService.registerUser(authDTO);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasRole('SECRETARY')")
    @PostMapping("/register-security")
    public ResponseEntity<String> registerSecurity(@RequestBody AuthDTO authDTO) {
        authDTO.setRoles(List.of("ROLE_SECURITY"));
        String result = userService.registerUser(authDTO);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthDTO authDTO) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authDTO.getUsername(), authDTO.getPassword()
                    )
            );

            UserDetails userDetails = userDetailsService.loadUserByUsername(authDTO.getUsername());
            String token = jwtUtil.generateToken(userDetails);

            return ResponseEntity.ok(Map.of("token", token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid username or password"));
        }
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(Authentication authentication) {
        if (authentication == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Not authenticated"));
        }

        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return ResponseEntity.ok(Map.of(
                "username", authentication.getName(),
                "roles", roles
        ));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admins")
    public ResponseEntity<List<String>> getAllAdmins() {
        return ResponseEntity.ok(userService.getAllAdmins());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'SECRETARY')")
    @GetMapping("/active-users")
    public ResponseEntity<?> getActiveUsers() {
        return ResponseEntity.ok(userService.getActiveUsers());
    }

    @PatchMapping("/deactivate/{username}")
    public ResponseEntity<String> deactivateUser(
            @PathVariable String username,
            Authentication authentication) {

        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        boolean isAdmin = roles.contains("ROLE_ADMIN");
        boolean isSecretary = roles.contains("ROLE_SECRETARY");

        String result = userService.deactivateUser(username, isAdmin, isSecretary);
        if (result.startsWith("Error")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
        return ResponseEntity.ok(result);
    }

    @PatchMapping("/activate/{username}")
    public ResponseEntity<String> activateUser(
            @PathVariable String username,
            Authentication authentication) {

        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        boolean isAdmin = roles.contains("ROLE_ADMIN");
        boolean isSecretary = roles.contains("ROLE_SECRETARY");

        String result = userService.activateUser(username, isAdmin, isSecretary);
        if (result.startsWith("Error")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/auth/roles-debug")
    public ResponseEntity<?> showUserRoles(Authentication authentication) {
    if (authentication == null) {
        return ResponseEntity.status(401).body("Not authenticated");
    }

    return ResponseEntity.ok(Map.of(
        "username", authentication.getName(),
        "authorities", authentication.getAuthorities()
    ));
}

}
