package com.saranchenkov.accountReplenishment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Ivan on 16.06.2017.
 */

@Controller
public class AppController {

    @GetMapping({"/", "/login"})
    public String start(){
        return "index";
    }

    @GetMapping("/admin/users")
    public String users(){
        return "users";
    }

    @GetMapping("/admin/balances")
    public String balances(){
        return "balances";
    }

    @GetMapping("/user/account")
    public String user(){
        return "account";
    }
}
