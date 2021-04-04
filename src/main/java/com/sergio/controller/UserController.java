package com.sergio.controller;

import com.sergio.converter.UserConverter;
import com.sergio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.Principal;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserConverter userConverter;

    @GetMapping(value = "/current")
    public ResponseEntity getCurrentUser(Principal principal){
        return ResponseEntity.ok(userConverter.toDto(userService.getCurrentUser(principal.getName())));
    }
}
