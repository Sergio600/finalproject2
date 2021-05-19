package com.sergio.controller;

import com.sergio.converter.UserConverter;
import com.sergio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(value = "/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private UserService userService;
    private UserConverter userConverter;

    @Autowired
    public UserController(UserService userService,
                          UserConverter userConverter) {
        this.userService = userService;
        this.userConverter = userConverter;
    }

    @GetMapping(value = "/current")
    public ResponseEntity getCurrentUser(Principal principal) {
        return ResponseEntity.ok(userConverter.toDto(userService.getCurrentUser(principal.getName())));
    }
}
