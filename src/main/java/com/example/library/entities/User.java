package com.example.library.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
public class User implements Serializable {

    private static final long serialVersionUID = 8480595839967663206L;

    @Id
    private String id;
    private String firstname;
    private String lastname;
    private LocalDate birthdate;
    private String email;
    private String phone;
    private String username;
    private String password;
    @Transient
    private List<String> acl;

    public User () {}

    public User (String id, String firstname, String lastname, LocalDate birthdate, String email, String phone, String username, String password, List<String> acl) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.email = email;
        this.phone = phone;
        this.username = username;
        this.password = password;
        this.acl = acl;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getBirthdate() {
        return this.birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getAcl() {
        return this.acl;
    }

    public void setAcl(List<String> acl) {
        this.acl = acl;
    }
}
