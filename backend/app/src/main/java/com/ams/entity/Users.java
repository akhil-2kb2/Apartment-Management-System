package com.ams.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import com.ams.enums.Shift;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private List<String> roles;

    @Column(nullable = false)
    private boolean isActive = false;

    @Enumerated(EnumType.STRING)
    private Shift shift; // Only used for SECURITY
}
