package com.ams.entity;
import com.ams.enums.ResidencyStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "family_members")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FamilyMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String relation;

    private Integer age;

    private String contact;

    private String idProofType;

    private String idProofNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "residency_status", nullable = false)
    private ResidencyStatus residencyStatus = ResidencyStatus.RESIDENT;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "apartment_id", nullable = false)
    private Apartment apartment;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;

    
}
