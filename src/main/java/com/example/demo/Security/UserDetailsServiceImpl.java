package com.example.demo.Security;

import com.example.demo.DTOs.UserDto;
import com.example.demo.Entity.User;
import com.example.demo.Mapper.Mapper;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;
     
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        UserDto user = userService.getUserByEmail(username);
         
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
         
        return new MyUserDetails(user);
    }
 
}