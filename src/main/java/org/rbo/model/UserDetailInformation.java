package org.rbo.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author vitalii.levash
 */
@MappedSuperclass
public abstract class UserDetailInformation implements Serializable {


    @Column(name = "first_name",length = 100)
    private String firstName;
    @Column(name = "name",length = 100)
    private String name;
    @Column(name = "last_name",length = 100)
    private String lastName;
    @Column(name = "email",length = 100)
    private String email;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserDetailInformation{" +
                "firstName='" + firstName + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
