package com.sistema.api.service;

import com.sistema.api.dto.LoginDTO;
import com.sistema.api.dto.RegisterDTO;
import com.sistema.api.dto.UserDTO;
import com.sistema.api.exception.ResourceNotFoundException;
import com.sistema.api.exception.UserAlreadyExistsException;
import com.sistema.api.model.User;
import com.sistema.api.repository.UserRepository;
import com.sistema.api.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    /**
     * Registra um novo usuário
     */
    public UserDTO registerUser(RegisterDTO registerDTO) {
        // Verifica se o nome de usuário já existe
        if (userRepository.existsByUsername(registerDTO.getUsername())) {
            throw new UserAlreadyExistsException("Username já está em uso");
        }

        // Verifica se o email já existe
        if (userRepository.existsByEmail(registerDTO.getEmail())) {
            throw new UserAlreadyExistsException("Email já está em uso");
        }

        // Cria um novo usuário
        User user = new User();
        user.setName(registerDTO.getName());
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setRole(registerDTO.getRole());
        user.setUnidade(registerDTO.getUnidade());
        user.setActive(true);

        User savedUser = userRepository.save(user);
        
        return mapToDTO(savedUser);
    }

    /**
     * Autenticação do usuário e geração de token JWT
     */
    public Map<String, Object> authenticateUser(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginDTO.getUsername(),
                loginDTO.getPassword()
            )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Obter usuário após autenticação
        User user = userRepository.findByUsername(loginDTO.getUsername())
            .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        // Gerar JWT token
        String token = tokenProvider.generateToken(authentication);

        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("user", mapToDTO(user));

        return response;
    }

    /**
     * Busca todos os usuários
     */
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Busca usuário por ID
     */
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com id: " + id));
        return mapToDTO(user);
    }

    /**
     * Atualiza um usuário
     */
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com id: " + id));

        // Atualiza os campos
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setRole(userDTO.getRole());
        user.setUnidade(userDTO.getUnidade());
        user.setActive(userDTO.isActive());

        // Se a senha foi fornecida, atualiza
        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }

        User updatedUser = userRepository.save(user);
        return mapToDTO(updatedUser);
    }

    /**
     * Desativa um usuário (soft delete)
     */
    public void deactivateUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com id: " + id));
        
        user.setActive(false);
        userRepository.save(user);
    }

    /**
     * Converte entidade User para UserDTO
     */
    private UserDTO mapToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());
        userDTO.setUnidade(user.getUnidade());
        userDTO.setActive(user.isActive());
        // Não transferimos a senha por segurança
        return userDTO;
    }
}