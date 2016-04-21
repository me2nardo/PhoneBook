package com.lardi.dto;

/**
 * @author vitalii.levash
 */
public class FilterCriteria {
    private String mobPhone;
    private String phone;
    private String surname;
    private String name;
    private String lastname;
    private int user_id;

    public FilterCriteria(){ }

    public FilterCriteria(String lastname, String mobPhone, String name, String phone, String surname,int user_id) {
        this.lastname = lastname;
        this.mobPhone = mobPhone;
        this.name = name;
        this.phone = phone;
        this.surname = surname;
        this.user_id = user_id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMobPhone() {
        return mobPhone;
    }

    public void setMobPhone(String mobPhone) {
        this.mobPhone = mobPhone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
