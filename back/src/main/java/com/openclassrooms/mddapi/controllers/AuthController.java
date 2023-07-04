package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.exception.BadRequestException;
import com.openclassrooms.mddapi.payload.request.LoginRequest;
import com.openclassrooms.mddapi.payload.request.RegisterRequest;
import com.openclassrooms.mddapi.payload.response.JwtResponse;
import com.openclassrooms.mddapi.payload.response.MessageResponse;
import com.openclassrooms.mddapi.services.AuthServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthServiceImpl authServiceImpl;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            JwtResponse jwt = this.authServiceImpl.login(loginRequest);
            return ResponseEntity.ok(jwt);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(new MessageResponse("Unauthorized"));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest) {
        try {
            String response = this.authServiceImpl.register(registerRequest);
            return ResponseEntity.ok(new MessageResponse(response));
        } catch (BadRequestException exception) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: username or email already taken"));
        }
    }
}
