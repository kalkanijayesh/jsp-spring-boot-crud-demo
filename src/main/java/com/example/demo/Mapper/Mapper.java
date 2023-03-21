package com.example.demo.Mapper;

import com.example.demo.DTOs.UserDto;
import com.example.demo.Entity.User;
import com.example.demo.Entity.UserDetails;
import com.example.demo.Repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Mapper {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    public static UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setEmailId(user.getEmailId());
        userDto.setPassword(user.getPassword());

        if (user.getUserDetails() != null) {
            userDto.setFirstName(user.getUserDetails().getFirstName());
            userDto.setLastName(user.getUserDetails().getLastName());
        }
        return userDto;
    }

    public static UserDto mapToUserDtoOptional(Optional<User> user){
        UserDto userDto = new UserDto();
        userDto.setUserId(user.get().getUserId());
        userDto.setEmailId(user.get().getEmailId());
        userDto.setPassword(user.get().getPassword());

        if (user.get().getUserDetails() != null) {
            userDto.setFirstName(user.get().getUserDetails().getFirstName());
            userDto.setLastName(user.get().getUserDetails().getLastName());
            userDto.setDesignation(user.get().getUserDetails().getDesignation());
        }
        return userDto;
    }

    public static UserDto mapToUserDetailsDto(UserDetails user){
        UserDto userDto = new UserDto();
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setDesignation(user.getDesignation());

        return userDto;
    }

    public static User mapToUser(UserDto userDto){
        User user = new User();
        user.setEmailId(userDto.getEmailId());
        user.setPassword(userDto.getPassword());
        return user;
    }

    public static User mapToUserForUpdate(UserDto userDto){
        User user = new User();
        user.setEmailId(userDto.getEmailId());
        user.setPassword(userDto.getPassword());

        return user;
    }

    public static UserDetails mapToUserDetails(UserDto userDto){
        UserDetails user = new UserDetails();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setDesignation(userDto.getDesignation());
        return user;
    }

}
