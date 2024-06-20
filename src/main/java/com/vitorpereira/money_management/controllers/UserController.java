package com.vitorpereira.money_management.controllers;

import com.vitorpereira.money_management.entities.UserApplication;
import com.vitorpereira.money_management.entities.dtos.UserEditDto;
import com.vitorpereira.money_management.exceptions.ResourceNotFoundException;
import com.vitorpereira.money_management.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService service;

    private String getUsernameFromToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            return authentication.getName();
        }
        throw new ResourceNotFoundException("Username could not be retrieved from the token.");
    }


    @GetMapping(value = "")
    public ResponseEntity<UserApplication> validateToken(){
        String username = getUsernameFromToken();
        UserApplication user = service.getUserByUsername(username);
        return ResponseEntity.ok(user);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserApplication> editUser(@PathVariable Integer id, @RequestBody UserEditDto dto){
        UserApplication user = service.editUser(id, dto);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id){
        service.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
