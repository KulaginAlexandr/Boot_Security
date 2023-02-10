package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    private UserServiceImpl adminService;
    @Autowired
    public AdminController(UserServiceImpl adminService) {
        this.adminService = adminService;
    }
    @GetMapping
    public String showAdminPanel(ModelMap map) {
        map.addAttribute("list", adminService.getUserList());
        return "/admin/userlist";
    }

    @GetMapping(value = "/delete")
    public String deleteUserById(@RequestParam(value = "id") Long id) {
        adminService.deleteUserById(id);
        return "redirect:/admin";
    }

    @GetMapping(value = "/edit")
    public String getUserEditForm(@RequestParam(value = "id", required = false) Integer id, ModelMap model) {
        model.addAttribute("user", adminService.getUserById(id));
        model.addAttribute("allRoles", adminService.getRoleList());
        return "/admin/edit";
    }

    @GetMapping(value = "/createUser")
    public String getUserAddForm(ModelMap model) {
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", adminService.getRoleList());
        return "/admin/createUser";
    }

    @PostMapping(value = "/save")
    public String saveUser(@ModelAttribute("user") User user) {
        adminService.saveUser(user);
        return "redirect:/admin";
    }

    @PatchMapping(value = "/save")
    public String updateUser(@ModelAttribute("user") User user) {
        adminService.updateUser(user);
        return "redirect:/admin";
    }
}
