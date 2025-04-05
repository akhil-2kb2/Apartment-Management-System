package com.ams.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DriverDTO {
    private Long id;
    private String name;
    private String idProofType;
    private String idProofNumber;
    private String contact;
}
