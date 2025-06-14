package com.crimson.app.crimson.dto;

import java.time.LocalDateTime;


public class UserDto {

    private Long userId;
    private String lastName;
    private String firstName;
    private String middleName;
    private String address;
    private String status;
    private String email;
    private String passWordHash;
    private String phoneNumber;
    private String role;
    private LocalDateTime timestamp;
    private String profilePicture;
    private String authorities;


    public UserDto(Long userId, String lastName,
                   String firstName, String middleName,
                   String address, String status, String email,
                   String passWordHash, String phoneNumber, String role,
                   LocalDateTime timestamp, String profilePicture,
                   String authorities) {
        this.userId = userId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.address = address;
        this.status = status;
        this.email = email;
        this.passWordHash = passWordHash;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.timestamp = timestamp;
        this.profilePicture = profilePicture;
        this.authorities = authorities;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassWordHash() {
        return passWordHash;
    }

    public void setPassWordHash(String passWordHash) {
        this.passWordHash = passWordHash;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }
}
