package com.sistema.api.controller;

import com.sistema.api.dto.LoginDTO;
import com.sistema.api.dto.RegisterDTO;
import com.sistema.api.dto.UserDTO;
import com.sistema.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserService userService;

    /**
     * Endpoint para login de usuário
     */
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> authenticateUser(@Valid @RequestBody LoginDTO loginDTO) {
        Map<String, Object> response = userService.authenticateUser(loginDTO);
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para registro de usuário
     */
    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody RegisterDTO registerDTO) {
        UserDTO savedUser = userService.registerUser(registerDTO);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    /**
     * Endpoint para verificar validade do token
     */
    @GetMapping("/verify")
    public ResponseEntity<Void> verifyToken() {
        // A validação do token é feita pelo filtro JWT, se chegar aqui é porque o token é válido
        return ResponseEntity.ok().build();
    }
}