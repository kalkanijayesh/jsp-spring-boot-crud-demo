package com.example.demo.Service;

import com.example.demo.DTOs.UserDto;
import com.example.demo.Entity.UserDetails;
import com.example.demo.Mapper.Mapper;
import com.example.demo.Repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceByDatabase {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    public List<UserDto> getAllUserDetails()
    {
        List<UserDetails> users = userDetailsRepository.findAll();
        return users.stream().map(Mapper::mapToUserDetailsDto)
                .collect(Collectors.toList());

    }

    public void saveUserDetails(UserDetails user){
        userDetailsRepository.save(user);
    }

}
