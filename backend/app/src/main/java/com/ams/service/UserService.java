package com.ams.service;

import com.ams.dto.AuthDTO;
import com.ams.entity.Users;
import com.ams.enums.Shift;
import com.ams.repository.UsersRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements UserDetailsService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
                .map(SimpleGrantedAuthority::new)
                .toList();

        return new User(user.getUsername(), user.getPassword(), authorities);
    }

    public String registerUser(AuthDTO authDTO) {
        if (usersRepository.findByUsername(authDTO.getUsername()).isPresent()) {
            return "Username already exists!";
        }

        List<String> roles = authDTO.getRoles();
        Users user = new Users();
        user.setUsername(authDTO.getUsername());
        user.setPassword(passwordEncoder.encode(authDTO.getPassword()));
        user.setRoles(roles);

        if (roles.contains("ROLE_SECRETARY")) {
            boolean activeSecretaryExists = usersRepository.findAll().stream()
                    .anyMatch(u -> u.getRoles().contains("ROLE_SECRETARY") && u.isActive());
            if (activeSecretaryExists) {
                return "Only 1 secretary can be active at a time.";
            }
            user.setActive(true);
        }

        if (roles.contains("ROLE_SECURITY")) {
            Shift shift = authDTO.getShift();
            if (shift == null) {
                return "Shift is required for security role.";
            }

            long count = usersRepository.findAll().stream()
                    .filter(u -> u.getRoles().contains("ROLE_SECURITY"))
                    .filter(Users::isActive)
                    .filter(u -> shift.equals(u.getShift()))
                    .count();

            if (count >= 1) {
                return "Only 1 security can be active per shift: " + shift;
            }

            user.setShift(shift);
            user.setActive(true);
        }

        usersRepository.save(user);
        return "User registered successfully!";
    }

    public String loginUser(AuthDTO authDTO) {
        Optional<Users> userOptional = usersRepository.findByUsername(authDTO.getUsername());

        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            if (passwordEncoder.matches(authDTO.getPassword(), user.getPassword())) {
                return "Login successful!";
            }
        }
        return "Invalid username or password!";
    }

    public List<String> getAllAdmins() {
        return usersRepository.findAll().stream()
                .filter(user -> user.getRoles().contains("ROLE_ADMIN"))
                .map(Users::getUsername)
                .toList();
    }

    public List<Map<String, Object>> getActiveUsers() {
        return usersRepository.findAll().stream()
                .filter(Users::isActive)
                .map(user -> Map.of(
                        "username", user.getUsername(),
                        "roles", user.getRoles(),
                        "shift", user.getShift() != null ? user.getShift().name() : "N/A"
                ))
                .toList();
    }

    public String deactivateUser(String username, boolean isAdmin, boolean isSecretary) {
        Optional<Users> userOpt = usersRepository.findByUsername(username);
        if (userOpt.isEmpty()) return "Error: User not found.";

        Users user = userOpt.get();

        if (!user.isActive()) return "Error: User is already inactive.";

        List<String> roles = user.getRoles();

        if ((roles.contains("ROLE_ADMIN") || roles.contains("ROLE_SECRETARY")) && !isAdmin) {
            return "Only ADMIN can deactivate this user.";
        }

        if (roles.contains("ROLE_SECURITY") && !isSecretary && !isAdmin) {
            return "Only secretary or admin can deactivate a security.";
        }

        user.setActive(false);
        usersRepository.save(user);
        return "User " + username + " deactivated successfully.";
    }

    public String activateUser(String username, boolean isAdmin, boolean isSecretary) {
        Optional<Users> userOptional = usersRepository.findByUsername(username);
    
        if (userOptional.isEmpty()) {
            return "Error: User not found";
        }
    
        Users user = userOptional.get();
    
        if (user.isActive()) {
            return "Error: User is already active";
        }
    
        if (user.getRoles().contains("ROLE_SECRETARY")) {
            if (!isAdmin) {
                return "Only admin can activate a secretary.";
            }
    
            boolean activeSecretaryExists = usersRepository.findAll().stream()
                    .anyMatch(u -> u.getRoles().contains("ROLE_SECRETARY") && u.isActive());
    
            if (activeSecretaryExists) {
                return "Only 1 secretary can be active at a time.";
            }
    
            user.setActive(true);
            usersRepository.save(user);
            return "User " + username + " activated successfully.";
        }
    
        if (user.getRoles().contains("ROLE_SECURITY")) {
            if (!isSecretary) {
                return "Only secretary can activate a security.";
            }
    
            Shift shift = user.getShift();
            if (shift == null) {
                return "Error: Security has no shift assigned.";
            }
    
            long count = usersRepository.findAll().stream()
                    .filter(u -> u.getRoles().contains("ROLE_SECURITY"))
                    .filter(Users::isActive)
                    .filter(u -> shift.equals(u.getShift()))
                    .count();
    
            if (count >= 1) {
                return "Only 1 security can be active per shift: " + shift;
            }
    
            user.setActive(true);
            usersRepository.save(user);
            return "User " + username + " activated successfully.";
        }
    
        return "Error: This user role cannot be activated manually.";
    }
    
}
