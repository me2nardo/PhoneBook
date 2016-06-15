package org.rbo.model;

import org.rbo.util.validate.Phone;
import org.hibernate.validator.constraints.Email;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author vitalii.levash
 */
@Entity
@Document(indexName = "phone",type = "item",shards = 1, replicas = 0)
public class PhoneBook {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(length = 60)
    private String userInfo;
    @Column(nullable = false, length = 30)
    @NotNull
    @Size(min = 3)
    private String surname;
    @Column(nullable = false, length = 30)
    @NotNull
    @Size(min = 3)
    private String name;
    @Column(nullable = false, length = 30)
    private String lastname;
    @Column(length = 100)
    private String address;
    @Column(nullable = false, length = 15)
    @NotNull
    @Phone(message = "Allowed format +38(066)1234567 only UA")
    private String mobPhone;
    @Column(length = 15)
    @Phone(message = "Allowed format +38(066)1234567 only UA")
    private String phone;
    @Column(length = 30)
    @Email
    private String email;

    @Field(type = FieldType.Nested)
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "userid")
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
