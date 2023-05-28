package com.rosariob.springboot.cruddemo.rest;

import com.rosariob.springboot.cruddemo.dto.UserDto;
import com.rosariob.springboot.cruddemo.entity.User;
import com.rosariob.springboot.cruddemo.error.UserAlreadyExistException;
import com.rosariob.springboot.cruddemo.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RegistrationController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserService userService;

    // Registration
    @PostMapping("/api/user/registration")
    public ResponseEntity registerUserAccount(@Valid @RequestBody final UserDto accountDto) {
        try {
            LOGGER.debug("Registering user account with information: {}", accountDto);
            final User registered = userService.registerNewUserAccount(accountDto);
            return ResponseEntity.ok("User " + registered.getUserName() + " registered successfully");
        }
        catch (UserAlreadyExistException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
