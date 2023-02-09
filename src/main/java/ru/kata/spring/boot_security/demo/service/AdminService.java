package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

@Service
public class AdminService implements UserService {
    private final UserDao userDao;
    private final RoleDao roleDao;

    public List<Role> getRoleList() {
        return roleDao.findAll();
    }

    @Autowired
    public AdminService(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        userDao.save(user);
    }

    @Transactional
    @Override
    public void deleteUserById(long id) {
        userDao.deleteById(id);
    }

    @Override
    public List<User> getUserList() {
        return userDao.findAll();
    }

    @Override
    public User getUserById(long id) {
        return userDao.getOne(id);
    }


}
