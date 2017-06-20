package com.saranchenkov.accountReplenishment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Ivan on 16.06.2017.
 */

@Controller
public class AppController {

    @GetMapping("/")
    public String start(){
        return "index";
    }

    @GetMapping("/balances")
    public String balances(){
        return "balances";
    }
}
