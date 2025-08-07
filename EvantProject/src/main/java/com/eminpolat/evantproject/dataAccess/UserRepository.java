package com.eminpolat.evantproject.dataAccess;

import com.eminpolat.evantproject.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username); // login işlemi için lazım olacak
}