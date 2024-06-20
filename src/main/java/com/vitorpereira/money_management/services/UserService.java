package com.vitorpereira.money_management.services;

import com.vitorpereira.money_management.entities.UserApplication;
import com.vitorpereira.money_management.entities.dtos.UserEditDto;
import com.vitorpereira.money_management.exceptions.DatabaseException;
import com.vitorpereira.money_management.exceptions.ResourceNotFoundException;
import com.vitorpereira.money_management.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("in the user details service");

        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user is not valid"));
    }

    public UserApplication getUserByUsername(String username){
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not found"));
    }

    public UserApplication getUserById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("user with id " + id + " not found"));
    }

    public UserApplication updateBalance(Double value, Integer id){
        try{
            UserApplication user = userRepository.getReferenceById(id);

            Double aux = user.getBalance() + value;
            user.setBalance(aux);

            return userRepository.save(user);
        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    public UserApplication editUser(Integer id, UserEditDto dto){
        try{
            UserApplication user = userRepository.getReferenceById(id);
            user.setUsername(dto.getUsername());
            user.setPassword(encoder.encode(dto.getPassword()));
            return userRepository.save(user);
        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    public void deleteUserById(Integer id){
        try{
            userRepository.deleteById(id);
        }
        catch(EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        }
        catch(DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }
}
