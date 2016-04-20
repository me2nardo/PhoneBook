package com.lardi.model;

import com.lardi.util.validate.Login;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author vitalii.levash
 */
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(unique = true, nullable = false, length = 30)
    @NotNull
    @Size(min = 5)
    @Login(message = "Login must me Latin")
    private String login;
    @Column(nullable = false, length = 40)
    @NotNull
    private String password;
    @Column(nullable = false, length = 100)
    @NotNull
    private String fio;

    @OneToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
    @JoinColumn(name = "userid")
    private List<PhoneBook> phoneBookList;


    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public List<PhoneBook> getPhoneBookList() {
        return phoneBookList;
    }

    public void setPhoneBookList(List<PhoneBook> phoneBookList) {
        this.phoneBookList = phoneBookList;
    }


}
