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
    private UserService userService;

    @Autowired
    private UserDetailsServiceByDatabase userDetailsService;

    @Autowired
    private Mapper mapper;

    @RequestMapping(value = "/login")
    private String login() {
        return "login";
    }

    @RequestMapping("/home")
    public String home(Model model) {
        List<UserDto> users = userService.getAllUser();
        String currentRole = userService.getRoleOfLoggedInUser();
        model.addAttribute("users", users);
        model.addAttribute("role", currentRole);
        model.addAttribute("viewAccess", true);
        model.addAttribute("editAccess", (currentRole.equals("ROLE_MANAGER")
                || currentRole.equals("ROLE_DY_MANAGER")));
        model.addAttribute("deleteAccess", currentRole.equals("ROLE_MANAGER"));
        return "home";
    }

    @RequestMapping("/view/{id}")
    public String updateUser(@PathVariable("id") String userId, Model model) throws IOException {
        UserDto userById = userService.getUserById(Integer.parseInt(userId.substring(3)));
        model.addAttribute("user", userById);
        System.out.println(userById);
        return "view";
    }

    @RequestMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") String userId, HttpServletResponse resp) throws IOException {
        userService.delete(Integer.parseInt(userId.substring(3)));
        resp.sendRedirect("/home");
    }

    // =====================================================

    // TODO: 22/03/23 Check why /createRecord and /createUser two different endpoints ??
    @RequestMapping(value = "/createRecord")
    public void saveUser(@ModelAttribute("user") UserDto user, Model model, HttpServletResponse resp) throws IOException {
        User interUser = Mapper.mapToUser(user);
        UserDetails interUserDetail = Mapper.mapToUserDetails(user);
        User user1 = userService.saveUser(interUser);
        interUserDetail.setUser(user1);
        userDetailsService.saveUserDetails(interUserDetail);
        List<UserDto> users = userService.getAllUser();
        model.addAttribute("ListOfUsers", users);
        resp.sendRedirect("/home");
    }

    @RequestMapping(value = "/createUser")
    public String createUser(Model model) {
        Designation[] values = Designation.values();
        model.addAttribute("user", new UserDto());
        model.addAttribute("designations", values);
        return "createUser";
    }


    // TODO: 22/03/23 Please handle edit action with save/create_user rest endpoint. (Or check online how we can handle new or edit record with the same method/functions
    @RequestMapping("/edit/{id}")
    public String editUser(@PathVariable("id") String userId, Model model) throws IOException {
        System.out.println(userId.substring(3));
        UserDto userById = userService.getUserById(Integer.parseInt(userId.substring(3)));
        model.addAttribute("user", userById);

        // TODO: 22/03/23 We need to handle the designation automatically instead of manually !!
        model.addAttribute("selectedDesignation", userById.getDesignation().name());
        model.addAttribute("userDtoObject", new UserDto());
        model.addAttribute("designations",
                Arrays.asList(Designation.MANAGER.name(), Designation.DY_MANAGER.name(), Designation.WORKER.name()));
        System.out.println(userById);
        return "edit";
    }

    @RequestMapping(value = "/editedRecord")
    public void editUser(@ModelAttribute("userDtoObject") UserDto userDtoObject, Model model, HttpServletResponse resp) throws IOException {
        Integer userId = userDtoObject.getUserId();
        userService.updateUserById(userId, userDtoObject);
        List<UserDto> users = userService.getAllUser();
        model.addAttribute("ListOfUsers", users);
        resp.sendRedirect("/home");
    }

    // TODO: 22/03/23 Please remove unwanted code like /createRecordBeforeLogin, /createUserBeforeLogin and other place too !!
    /*@RequestMapping("/createRecordBeforeLogin")
    public void createRecordBeforeLogin(@ModelAttribute("user") UserDto user, Model model, HttpServletResponse resp) throws IOException {
        System.out.println(user);
        User interUser = Mapper.mapToUser(user);
        UserDetails interUserDetail = Mapper.mapToUserDetails(user);
        User user1 = userService.saveUser(interUser);
        interUserDetail.setUser(user1);
        userDetailsService.saveUserDetails(interUserDetail);
        System.out.println("user saved");
        List<UserDto> users = userService.getAllUser();
        model.addAttribute("ListOfUsers", users);
        resp.sendRedirect("/openPage");
    }

    @RequestMapping(value = "/createUserBeforeLogin")
    public String createUserBeforeLogin(Model model) {
        Designation[] values = Designation.values();
        model.addAttribute("user", new UserDto());
        model.addAttribute("designations", values);
        return "CreateUserBeforeLogin";
    }*/
}
