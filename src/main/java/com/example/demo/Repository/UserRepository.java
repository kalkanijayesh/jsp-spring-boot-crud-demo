package com.example.demo.Repository;

import com.example.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

  /*  public void updateUserById(int id, UserDto userDto);*/

    Optional<User> findByEmailId(String email);
}
