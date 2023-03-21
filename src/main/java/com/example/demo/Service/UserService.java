package com.example.demo.Service;

import com.example.demo.DTOs.UserDto;
import com.example.demo.Entity.User;
import com.example.demo.Mapper.Mapper;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserDto> getAllUser() {
        List<User> users = userRepository.findAll();
        return users.stream().map(Mapper::mapToUserDto)
                .collect(Collectors.toList());
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public UserDto getUserById(int id) {
        Optional<User> user = userRepository.findById(id);
        return Mapper.mapToUserDtoOptional(user);
    }

    public void updateUserById(int id, UserDto userDto) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User user1 = user.get();
            user1.getUserDetails().setFirstName(userDto.getFirstName());
            user1.getUserDetails().setLastName(userDto.getLastName());
            user1.getUserDetails().setDesignation(userDto.getDesignation());
            userRepository.save(user1);
        }
    }

    public void delete(int id) {
        userRepository.deleteById(id);
    }

    public UserDto getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmailId(email);
        return Mapper.mapToUserDtoOptional(user);
    }

}