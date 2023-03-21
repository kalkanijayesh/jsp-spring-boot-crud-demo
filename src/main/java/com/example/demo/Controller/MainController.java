package com.example.demo.Controller;

import com.example.demo.DTOs.UserDto;
import com.example.demo.Entity.User;
import com.example.demo.Entity.UserDetails;
import com.example.demo.Enums.Designation;
import com.example.demo.Mapper.Mapper;
import com.example.demo.Service.UserDetailsServiceByDatabase;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class MainController {

    @Autowired
    UserService userService;

    @Autowired
    UserDetailsServiceByDatabase userDetailsService;

    @Autowired
    Mapper mapper;

    @RequestMapping("/firstPage")
    public String firstPage(Model model){
        List<UserDto> users = userService.getAllUser();
        System.out.println(users);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        List<String> roles = authentication.getAuthorities().stream()
                .map(r -> r.getAuthority()).collect(Collectors.toList());
        System.out.println(roles.get(0));

        model.addAttribute("role", roles.get(0));
        model.addAttribute("ListOfUsers",users);
        return "firstPage";
    }

    @RequestMapping("/getAllUser")
    public List<UserDto> getAllUSer(){
        return userService.getAllUser();
    }

    @RequestMapping(value = "/createRecord")
    public void saveUser(@ModelAttribute("user") UserDto user, Model model, HttpServletResponse resp) throws IOException {
        System.out.println(user);
        User interUser = Mapper.mapToUser(user);
        UserDetails interUserDetail = Mapper.mapToUserDetails(user);
        User user1 = userService.saveUser(interUser);
        interUserDetail.setUser(user1);
        userDetailsService.saveUserDetails(interUserDetail);
        System.out.println("user saved");
        List<UserDto> users = userService.getAllUser();
        model.addAttribute("ListOfUsers",users);
        resp.sendRedirect("/firstPage");
    }

    @RequestMapping(value = "/createUser")
    public String createUser(Model model){
        Designation[] values = Designation.values();
        model.addAttribute("user", new UserDto());
        model.addAttribute("designations", values);
        return "createUser";
    }

    @RequestMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") String userId, HttpServletResponse resp) throws IOException {
       // System.out.println(userId.substring(3));
        userService.delete(Integer.parseInt(userId.substring(3)));
        resp.sendRedirect("/firstPage");
    }

    @RequestMapping("/edit/{id}")
    public String editUser(@PathVariable("id") String userId, Model model) throws IOException {
        System.out.println(userId.substring(3));
        UserDto userById = userService.getUserById(Integer.parseInt(userId.substring(3)));
        model.addAttribute("user", userById);
        model.addAttribute("selectedDesignation", userById.getDesignation().name());
        model.addAttribute("userDtoObject", new UserDto());
        model.addAttribute("designations", Arrays.asList(Designation.MANAGER.name(), Designation.DIV_MANAGER.name(), Designation.DEVELOPER.name()));
        System.out.println(userById);
        return "edit";

    }

    @RequestMapping(value = "/editedRecord")
    public void editUser(@ModelAttribute("userDtoObject") UserDto userDtoObject, Model model, HttpServletResponse resp) throws IOException {

        System.out.println(userDtoObject);
       Integer userId = userDtoObject.getUserId();
       userService.updateUserById(userId, userDtoObject);
        List<UserDto> users = userService.getAllUser();
        model.addAttribute("ListOfUsers",users);
        resp.sendRedirect("/firstPage");
    }

    @RequestMapping("/view/{id}")
    public String updateUser(@PathVariable("id") String userId, Model model) throws IOException {

        UserDto userById = userService.getUserById(Integer.parseInt(userId.substring(3)));
        model.addAttribute("user", userById);
        System.out.println(userById);
        return "view";
    }

    @RequestMapping(value = "/openPage")
    public String openPage(){
        return "openPage";
    }
    @RequestMapping("/createRecordBeforeLogin")
    public void createRecordBeforeLogin(@ModelAttribute("user") UserDto user, Model model, HttpServletResponse resp) throws IOException {
        System.out.println(user);
        User interUser = Mapper.mapToUser(user);
        UserDetails interUserDetail = Mapper.mapToUserDetails(user);
        User user1 = userService.saveUser(interUser);
        interUserDetail.setUser(user1);
        userDetailsService.saveUserDetails(interUserDetail);
        System.out.println("user saved");
        List<UserDto> users = userService.getAllUser();
        model.addAttribute("ListOfUsers",users);
        resp.sendRedirect("/openPage");
    }

    @RequestMapping(value = "/createUserBeforeLogin")
    public String createUserBeforeLogin(Model model){
        Designation[] values = Designation.values();
        model.addAttribute("user", new UserDto());
        model.addAttribute("designations", values);
        return "CreateUserBeforeLogin";
    }
}
