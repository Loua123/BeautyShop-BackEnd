package com.example.ecommercelouabackend.controllers;


import com.example.ecommercelouabackend.dto.RegisterSellerRequest;
import com.example.ecommercelouabackend.entities.AuthenticationRequest;
import com.example.ecommercelouabackend.entities.AuthenticationResponse;
import com.example.ecommercelouabackend.entities.RegisterRequest;
import com.example.ecommercelouabackend.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticateService;
    @PostMapping("authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticateService.authenticate(request));
    }
    @PostMapping("register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticateService.register(request));
    }
    @PostMapping("/registerSeller")
    public ResponseEntity<AuthenticationResponse> registerSeller (@RequestBody RegisterSellerRequest request)
    {
        return ResponseEntity.ok(authenticateService.registerSeller(request));
    }
}
