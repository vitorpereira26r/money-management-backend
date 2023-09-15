package com.vitorpereira.money_management.services;

import com.vitorpereira.money_management.entities.Role;
import com.vitorpereira.money_management.entities.UserApplication;
import com.vitorpereira.money_management.entities.dtos.CredentialDto;
import com.vitorpereira.money_management.entities.dtos.LoginResponseDto;
import com.vitorpereira.money_management.entities.dtos.RegisterUserDto;
import com.vitorpereira.money_management.repository.RoleRepository;
import com.vitorpereira.money_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public UserApplication registerUser(RegisterUserDto registerDto){

        String encodedPassword = encoder.encode(registerDto.getPassword());
        Role userRole = roleRepository.findByAuthority("USER").get();

        Set<Role> roles = new HashSet<>();
        roles.add(userRole);

        return userRepository.save(new UserApplication(0, registerDto.getUsername(), encodedPassword, roles));
    }

    public LoginResponseDto login(CredentialDto credentialDto){

        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(credentialDto.getUsername(), credentialDto.getPassword())
            );

            String token = tokenService.generateJwt(auth);

            return new LoginResponseDto(userRepository.findByUsername(credentialDto.getUsername()).get(), token);
        }
        catch (AuthenticationException e){
            return new LoginResponseDto(null, "");
        }
    }
}
