package com.lardi.model;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author vitalii.levash
 */
@Entity
public class PhoneBook {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(length = 60)
    private String userInfo;
    @Column(nullable = false, length = 30)
    @NotNull
    @Size(min = 4)
    private String surname;
    @Column(nullable = false, length = 30)
    @NotNull
    @Size(min = 4)
    private String name;
    @Column(nullable = false, length = 30)
    private String lastname;
    @Column(length = 100)
    private String address;
    @Column(nullable = false, length = 15)
    @NotNull
    private String mobPhone;
    @Column(length = 15)
    private String phone;
    @Column(length = 30)
    @Email
    private String email;
    @ManyToOne(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
    @JoinColumn(name = "userid")
    @NotNull
    private User user;


    public PhoneBook() { }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobPhone() {
        return mobPhone;
    }

    public void setMobPhone(String mobPhone) {
        this.mobPhone = mobPhone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
