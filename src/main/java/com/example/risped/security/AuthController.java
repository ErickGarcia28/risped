package com.example.risped.security;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        if ("admin".equals(authRequest.getUsername()) && "password123".equals(authRequest.getPassword())) {
            // Generar el token JWT
            String token = JwtUtils.generateToken(authRequest.getUsername());
            return ResponseEntity.ok(new AuthResponse(token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }
}

// Clase para la solicitud de autenticación (sin Lombok)
class AuthRequest {
    private String username;
    private String password;

    // Getters y Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

// Clase para la respuesta de autenticación (sin Lombok)
class AuthResponse {
    private String token;

    // Constructor
    public AuthResponse(String token) {
        this.token = token;
    }

    // Getter
    public String getToken() {
        return token;
    }
}
