package com.saranchenkov.accountReplenishment.controller;

import com.saranchenkov.accountReplenishment.model.User;
import com.saranchenkov.accountReplenishment.service.UserService;
import com.saranchenkov.accountReplenishment.to.UserTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by Ivan on 17.06.2017.
 */

@RestController
public class UserController {

    private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @GetMapping("/user")
    public ResponseEntity<UserTO> user(Principal principal) {
        final UserTO userTO = new UserTO(userService.getByEmail(principal.getName()));
        return new ResponseEntity<>(userTO, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid User user, BindingResult result){
        LOGGER.info("Registration of user: {}", user);

        if (Objects.isNull(user)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (result.hasErrors()){
            final List<String> messages = result.getAllErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.toList());
            return new ResponseEntity(messages, HttpStatus.BAD_REQUEST);
        }
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
