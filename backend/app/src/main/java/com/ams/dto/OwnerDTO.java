package com.ams.dto;

import java.util.List;

public class OwnerDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private List<Long> apartmentIds;

    public OwnerDTO() {
    }

    public OwnerDTO(Long id, String name, String email, String phone, List<Long> apartmentIds) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.apartmentIds = apartmentIds;
    }

    // Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Long> getApartmentIds() {
        return apartmentIds;
    }

    public void setApartmentIds(List<Long> apartmentIds) {
        this.apartmentIds = apartmentIds;
    }
}
