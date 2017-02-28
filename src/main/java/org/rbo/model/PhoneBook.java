package org.rbo.model;


import javax.persistence.*;
import java.io.Serializable;

/**
 * @author vitalii.levash
 */
@Entity
@Table(name = "phone_book")
public class PhoneBook extends UserDetailInformation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "info",length = 60)
    private String userInfo;
    @Column(name = "address",nullable = false, length = 30)
    private String address;
    @Column(name = "mobile_phone",nullable = false, length = 15)
    private String mobilePhone;
    @Column(name = "phone",length = 15)
    private String phone;
    @Column(name = "email",length = 30)
    private String email;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "userid")
    private User user;


    public PhoneBook() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhoneBook phoneBook = (PhoneBook) o;

        return id.equals(phoneBook.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
