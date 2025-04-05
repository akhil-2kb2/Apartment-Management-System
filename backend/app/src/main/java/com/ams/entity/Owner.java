package com.ams.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "owners")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String phone;

    private boolean isDeleted = false;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Apartment> apartments;
}
