package com.crimson.app.crimson.dto;


public class AddressRegionDto {

    private Long addressRegionId;
    private String regionName;

    public AddressRegionDto(Long addressRegionId, String regionName) {
        this.addressRegionId = addressRegionId;
        this.regionName = regionName;
    }

    public Long getAddressRegionId() {
        return addressRegionId;
    }

    public void setAddressRegionId(Long addressRegionId) {
        this.addressRegionId = addressRegionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }




    @Override
    public String toString() {
        return regionName;
    }
}
