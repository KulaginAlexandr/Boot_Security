package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.security.UserDetailsImpl;
import ru.kata.spring.boot_security.demo.service.AdminService;

@Controller
public class MainController {

    private AdminService adminService;

    @Autowired
    public MainController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/user")
    public String showUserInfo(ModelMap map) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        map.addAttribute("user", userDetails.getUser());
        return "user";
    }

    @GetMapping("/admin")
    public String showAdminPanel(ModelMap map) {
        map.addAttribute("list", adminService.getUserList());
        return "admin/userlist";
    }

    @GetMapping(value = "/admin/delete")
    public String deleteUserById(@RequestParam(value = "id") Long id) {
        adminService.deleteUserById(id);
        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/edit")
    public String getUserEditForm(@RequestParam(value = "id", required = false) Integer id, ModelMap model) {
        model.addAttribute("user", adminService.getUserById(id));
        System.out.println(adminService.getRoleList());
        model.addAttribute("allRoles", adminService.getRoleList());
        return "/admin/edit";
    }

    @GetMapping(value = "/admin/createUser")
    public String getUserAddForm(ModelMap model) {
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", adminService.getRoleList());
        return "/admin/createUser";
    }

    @PostMapping(value = "/admin/save")
    public String saveUser(@ModelAttribute("user") User user) {
        adminService.saveUser(user);
        return "redirect:/admin";
    }

    @PatchMapping(value = "/admin/save")
    public String updateUser(@ModelAttribute("user") User user) {
        adminService.saveUser(user);
        return "redirect:/admin";
    }
}
