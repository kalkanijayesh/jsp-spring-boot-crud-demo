package com.example.demo.Entity;

import com.example.demo.Enums.Designation;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userDetailId;

    private String firstName;
    private String lastName;
    private Designation designation;

    @OneToOne
    @JoinColumn(name = "user_id", unique= true)
    private User user;

    public UserDetails() {
    }

    public UserDetails(Integer userDetailId, String firstName, String lastName, Designation designation, User user) {
        this.userDetailId = userDetailId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.designation = designation;
        this.user = user;
    }

    public Integer getUserDetailId() {
        return userDetailId;
    }

    public void setUserDetailId(Integer userDetailId) {
        this.userDetailId = userDetailId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Designation getDesignation() {
        return designation;
    }

    public void setDesignation(Designation designation) {
        this.designation = designation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "userDetailId=" + userDetailId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", designation=" + designation +
                ", user=" + user +
                '}';
    }
}
