package com.sistema_de_alertas.Sistema_de_Alertas.auth.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthLoginRequest {
    @Email(message = "Debe ser un mail válido")
    private String mail;

    @NotNull(message = "Sin contraseña")
    private String password;
}
