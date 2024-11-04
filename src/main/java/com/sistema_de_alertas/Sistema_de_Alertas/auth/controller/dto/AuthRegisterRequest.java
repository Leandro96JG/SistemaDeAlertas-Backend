package com.sistema_de_alertas.Sistema_de_Alertas.auth.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRegisterRequest {
    @NotNull(message = "Usuario sin nombre")
    private String name;

    @NotNull(message = "Usuario sin apellido")
    private String lastname;

    @NotNull(message = "Usuario sin username")
    private String username;

    @Email(message = "Mail no valido")
    @NotNull(message = "Usuario sin Mail")
    private String mail;

    @NotNull(message = "Ingrese password")
    private String password;
}
