package com.anstay.entity;

import com.anstay.dto.UserDTO;
import com.anstay.enums.Role;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String phone;

    @Column(nullable = false)
    private String password;

    private String avatar;
    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String verificationCode;
    private java.sql.Timestamp codeExpiresAt;
    private Boolean isVerified = false;

    public User(Integer id, String fullName, String email, String phone, String password, String avatar, String address, Role role, String verificationCode, Timestamp codeExpiresAt, Boolean isVerified) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.avatar = avatar;
        this.address = address;
        this.role = role;
        this.verificationCode = verificationCode;
        this.codeExpiresAt = codeExpiresAt;
        this.isVerified = isVerified;
    }

    public User(UserDTO userDTO) {
    }

    public User() {

    }

// Getters and Setters


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public Timestamp getCodeExpiresAt() {
        return codeExpiresAt;
    }

    public void setCodeExpiresAt(Timestamp codeExpiresAt) {
        this.codeExpiresAt = codeExpiresAt;
    }

    public Boolean getVerified() {
        return isVerified;
    }

    public void setVerified(Boolean verified) {
        isVerified = verified;
    }
}