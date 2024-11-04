package com.sistema_de_alertas.Sistema_de_Alertas.auth.controller.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonPropertyOrder({"username","message","jwt","status"})
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    private String username;

    private String message;

    private String jwt;

    private boolean status;
}
