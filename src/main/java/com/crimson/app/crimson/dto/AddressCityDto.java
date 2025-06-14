package com.crimson.app.crimson.dto;

public class AddressCityDto {

    private Long addressCityMunicipalityId;
    private String cityName;

    public AddressCityDto(Long addressCityMunicipalityId, String cityName) {
        this.addressCityMunicipalityId = addressCityMunicipalityId;
        this.cityName = cityName;
    }

    public Long getAddressCityMunicipalityId() {
        return addressCityMunicipalityId;
    }

    public void setAddressCityMunicipalityId(Long addressCityMunicipalityId) {
        this.addressCityMunicipalityId = addressCityMunicipalityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return cityName;
    }
}
