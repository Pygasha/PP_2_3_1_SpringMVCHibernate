package com.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.web.model.User;
import com.web.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String showAllUser(Model model) {
        model.addAttribute("allUsers", userService.getAllUsers());
        return "allUsers";
    }

    @GetMapping(value = "/addNewUser")
    public String addNewUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "userInfo";
    }

    @PostMapping
    public String saveNewUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", userService.showUserById(id));
        return "userInfo";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") int id) {
        userService.deleteUserById(id);
        return "redirect:/users";
    }

}
