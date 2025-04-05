package com.ams.dto;

public class ApartmentDTO {
    private Long id;
    private String name;
    private String address;
    private String apartmentType;
    private int floorNumber;
    private boolean isOccupied;

    public ApartmentDTO() {}

    public ApartmentDTO(Long id, String name, String address, String apartmentType, int floorNumber, boolean isOccupied) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.apartmentType = apartmentType;
        this.floorNumber = floorNumber;
        this.isOccupied = isOccupied;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getApartmentType() { return apartmentType; }
    public void setApartmentType(String apartmentType) { this.apartmentType = apartmentType; }

    public int getFloorNumber() { return floorNumber; }
    public void setFloorNumber(int floorNumber) { this.floorNumber = floorNumber; }

    public boolean isOccupied() { return isOccupied; }
    public void setOccupied(boolean occupied) { isOccupied = occupied; }
}
