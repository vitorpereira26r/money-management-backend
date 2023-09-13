package com.vitorpereira.money_management.services;

import com.vitorpereira.money_management.entities.Role;
import com.vitorpereira.money_management.entities.UserApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("in the user details service");

        if(!username.equals("Vitor")) throw new UsernameNotFoundException("Username not Vitor");

        Set<Role> roles = new HashSet<>();
        roles.add(new Role(0, "USER"));

        return new UserApplication(0, "Vitor", encoder.encode("password"), roles);
    }
}
