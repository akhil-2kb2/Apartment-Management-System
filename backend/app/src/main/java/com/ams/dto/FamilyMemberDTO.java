package com.ams.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class FamilyMemberDTO {

    private Long id;
    private String name;
    private String relation;
    private Long apartmentId;
    private Integer age;
    private String contact;
    private String idProofType;
    private String idProofNumber;
    private String residencyStatus;
    private Long ownerId;
    private Long tenantId;
}
