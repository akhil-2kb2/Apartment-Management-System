package com.ams.dto;

public class TenantDTO {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private Long apartmentId;

    // ✅ No-args constructor
    public TenantDTO() {
    }

    // ✅ All-args constructor (this is what you need!)
    public TenantDTO(Long id, String name, String email, String phone, Long apartmentId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.apartmentId = apartmentId;
    }

    // ✅ Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public Long getApartmentId() { return apartmentId; }
    public void setApartmentId(Long apartmentId) { this.apartmentId = apartmentId; }
}
