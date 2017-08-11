package com.saranchenkov.accountReplenishment.to;

import com.saranchenkov.accountReplenishment.model.User;

/**
 * Created by Ivan on 22.06.2017.
 */
public class UserTO {
    private String email;
    private Double balance;

    public UserTO(){}

    public UserTO(User user) {
        this.email = user.getEmail();
        this.balance = user.getBalance();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
