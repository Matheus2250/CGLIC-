package com.cglic.repository;

import com.cglic.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Busca um usuário pelo nome de usuário
    Optional<User> findByUsername(String username);
    
    // Verifica se já existe um usuário com este nome de usuário
    boolean existsByUsername(String username);
    
    // Verifica se já existe um usuário com este email
    boolean existsByEmail(String email);
}