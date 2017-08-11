package com.saranchenkov.accountReplenishment.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
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
    @Email(message = "Invalid email address.")
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 6, max = 20, message = "Password size must be between 6 and 20")
    private String password;

    @Column(name = "registerDate", columnDefinition = "date default current_date()")
    private Date registerDate = new Date();

    private Role role = Role.USER;

    @Column(columnDefinition = "decimal(10, 2) default '0.00'")
    private Double balance = 0.00;

    public User(){}

    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

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
