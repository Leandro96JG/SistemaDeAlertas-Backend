package com.sistema_de_alertas.Sistema_de_Alertas.app.model.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlertRedResponse {
    private Long id;

    private String date;

    private String description;
}
