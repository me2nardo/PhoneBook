package org.rbo.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 * @author vitalii.levash
 */
@Entity
@Table(name = "user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "user_name",nullable = false,unique = true,updatable = false)
    private String username;
    @Column(name = "password",nullable = false, length = 100)
    private String password;
    @Column(name = "first_name",nullable = false,length = 40)
    private String firstName;
    @Column(name = "last_name",nullable = false,length = 40)
    private String lastName;

    @Column(name = "account_non_expired",length = 1)
    private boolean accountNonExpired;
    @Column(name = "account_non_locked",length = 1)
    private boolean accountNonLocked;
    @Column(name = "credentials_non_expired",length = 1)
    private boolean credentialsNonExpired;
    @Column(name = "enable",length = 1)
    private boolean enabled;

    @OneToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY,mappedBy = "user")
    private List<PhoneBook> phoneBookList;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    //Todo:: Implement authorities
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public List<PhoneBook> getPhoneBookList() {
        return phoneBookList;
    }

    public void setPhoneBookList(List<PhoneBook> phoneBookList) {
        this.phoneBookList = phoneBookList;
    }
}
