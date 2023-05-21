package com.ghostman.ecommerce.fpl.repository;

import com.ghostman.ecommerce.fpl.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserByEmail(String email);
}
