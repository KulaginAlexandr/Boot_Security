package ru.kata.spring.boot_security.demo.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class initDefaultUsers {
    private UserServiceImpl userService;
    private RoleServiceImpl roleService;

    @Autowired
    public initDefaultUsers(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    private void postConstruct(){
        if (userService.findByEmail("admin@ya.ru").isEmpty()) {
            List<Role> roleList = roleService.getRoleList();
            User admin = new User("admin", "admin", (byte) 0, "admin@ya.ru", "");
            userService.saveUser(admin);
            admin.setPassword("1");
            admin.addRole(roleList.get(0));
            userService.updateUser(admin);
        }
    }
}