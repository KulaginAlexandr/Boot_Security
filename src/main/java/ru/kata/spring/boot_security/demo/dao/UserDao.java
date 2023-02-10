package ru.kata.spring.boot_security.demo.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    @Query("select u from user u join fetch u.roles where u.email =:login")
    Optional<User> findByEmail(String login);
}
