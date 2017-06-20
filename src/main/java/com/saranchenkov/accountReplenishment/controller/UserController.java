package com.saranchenkov.accountReplenishment.controller;

import com.saranchenkov.accountReplenishment.model.Balance;
import com.saranchenkov.accountReplenishment.model.User;
import com.saranchenkov.accountReplenishment.service.BalanceService;
import com.saranchenkov.accountReplenishment.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Ivan on 17.06.2017.
 */

@RestController
public class UserController {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @Autowired
    BalanceService balanceService;

    @GetMapping(value = "/users/{page}")
    public ResponseEntity<Page<User>> listAllUsers(@PathVariable("page") int pageNumber) {
        LOGGER.info("Method listAllUsers in UserController.class");
        final Page<User> page = userService.getCurrentPage(pageNumber);

        if (!page.hasContent()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @PostMapping(value = "/user/{id}")
    public ResponseEntity<?> putMoney(@RequestParam("amount") int amount,
                                      @PathVariable("id")int id,
                                      @RequestParam("newBalance") double newBalance,
                                      @RequestBody Balance balance){
        if (balance == null) {
            LOGGER.error("Unable to update. Balance of User(id={})is null.", id);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        userService.update(newBalance, id);
        LOGGER.info("Updating User with id {}", id);
        balanceService.save(balance);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/balances/{page}")
    public ResponseEntity<Page<Balance>> listAllBalances(@PathVariable("page") int pageNumber) {
        LOGGER.info("Method listAllBalances in UserController.class");
        final Page<Balance> page = balanceService.getCurrentPage(pageNumber);

        if (!page.hasContent()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping(value = "/balances/filter/{page}")
    public ResponseEntity<Page<Balance>> filter(@PathVariable("page") int pageNumber,
                                                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam("startDate") Date startDate,
                                                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam("endDate") Date endDate){

        final Page<Balance> page = balanceService.getFilterPage(pageNumber, startDate, endDate);

        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    private static List<User> convert(Page<User> page){
        List<User> list = new ArrayList<>();
        page.forEach(list::add);
        list.forEach(u -> LOGGER.info(u.toString()));
        return list;
    }

}
