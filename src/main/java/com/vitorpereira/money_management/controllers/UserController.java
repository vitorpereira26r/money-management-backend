package com.vitorpereira.money_management.controllers;

import com.vitorpereira.money_management.entities.UserApplication;
import com.vitorpereira.money_management.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<String> userAccess(){
        return ResponseEntity.ok("User level access");
    }

    @GetMapping(value = "/validate/{id}")
    public ResponseEntity<UserApplication> validateToken(@PathVariable Integer id){
        UserApplication user = service.getUserById(id);
        return ResponseEntity.ok(user);
    }
}
