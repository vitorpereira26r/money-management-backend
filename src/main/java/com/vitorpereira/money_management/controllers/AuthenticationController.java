package com.vitorpereira.money_management.controllers;

import com.vitorpereira.money_management.entities.UserApplication;
import com.vitorpereira.money_management.entities.dtos.CredentialDto;
import com.vitorpereira.money_management.entities.dtos.LoginResponseDto;
import com.vitorpereira.money_management.entities.dtos.RegisterUserDto;
import com.vitorpereira.money_management.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth")
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationService service;

    @GetMapping("/test/{name}")
    public ResponseEntity<String> test(@PathVariable String name){
        return ResponseEntity.ok("Hello " + name);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<UserApplication> register(@RequestBody RegisterUserDto registerDto){
        UserApplication user = service.registerUser(registerDto);
        return ResponseEntity.ok(user);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody CredentialDto dto) {
        LoginResponseDto loginResponse = service.login(dto);
        return ResponseEntity.ok(loginResponse);
    }
}
