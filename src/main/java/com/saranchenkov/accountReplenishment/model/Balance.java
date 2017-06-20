package com.saranchenkov.accountReplenishment.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Ivan on 16.06.2017.
 */

@Entity
@Table(name = "balances")
public class Balance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "decimal(10, 2) default 0.00")
    private Double amount;

    @Column(name = "date", columnDefinition = "date default current_date()")
    //    @Type(type = "java.time.LocalDate")
    private Date date;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "admin_email")
    private String adminEmail;

    public Balance(){}

    public Balance(Double amount, Date date, String userEmail, String adminEmail) {
        this.amount = amount;
        this.date = date;
        this.userEmail = userEmail;
        this.adminEmail = adminEmail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    @Override
    public String toString() {
        return "Balance{" +
                "id=" + id +
                ", amount=" + amount +
                ", date=" + date +
                ", userEmail='" + userEmail + '\'' +
                ", adminEmail='" + adminEmail + '\'' +
                '}';
    }
}
