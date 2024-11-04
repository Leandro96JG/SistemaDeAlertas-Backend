package com.sistema_de_alertas.Sistema_de_Alertas.auth.controller;

import com.sistema_de_alertas.Sistema_de_Alertas.auth.controller.dto.AuthLoginRequest;
import com.sistema_de_alertas.Sistema_de_Alertas.auth.controller.dto.AuthRegisterRequest;
import com.sistema_de_alertas.Sistema_de_Alertas.auth.controller.dto.AuthResponse;
import com.sistema_de_alertas.Sistema_de_Alertas.auth.services.UserDetailsServiceImpl;
import jakarta.annotation.security.DenyAll;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@PreAuthorize("denyAll()")
public class AuthController {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @GetMapping
    @PreAuthorize("permitAll()")
    public String probando(){
        return "Hola giles";
    }

    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthLoginRequest userRequest){
        return new ResponseEntity<>(this.userDetailsService.loginUser(userRequest),HttpStatus.OK);
    }

    @PostMapping("/register")
    @PreAuthorize("permitAll()")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid AuthRegisterRequest userRequest){
        return new ResponseEntity<>(this.userDetailsService.createUser(userRequest), HttpStatus.CREATED);
    }

    @GetMapping("/hello")
    @PreAuthorize("hasRole('CUSTOMER')")
    public String helloWord(){
        return "Hello Word User";
    }

}
