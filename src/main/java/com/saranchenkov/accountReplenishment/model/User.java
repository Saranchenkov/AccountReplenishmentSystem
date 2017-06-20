package com.saranchenkov.accountReplenishment.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

/**
 * Created by Ivan on 16.06.2017.
 */

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String email;

    private String password;

    @Column(name = "registerDate", columnDefinition = "date default current_date()")
//    @Type(type = "java.time.LocalDate")
    private Date registerDate;

    private Role role;

    @Column(columnDefinition = "decimal(10, 2) default 0.00")
    private Double balance;

/*
    public User(User u){
        this(u.getId(), u.getEmail(), u.getPassword(), u.getBalance(), u.getRegisterDate(), u.getRole());
    }
*/
    public User(){}

    public User(Integer id, String email, String password, Double balance, Date registerDate, Role role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.balance = balance;
        this.registerDate = registerDate;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                ", registerDate=" + registerDate +
                ", role=" + role +
                '}';
    }
}
