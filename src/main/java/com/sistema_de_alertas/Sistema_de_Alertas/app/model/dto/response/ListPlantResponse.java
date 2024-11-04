package com.sistema_de_alertas.Sistema_de_Alertas.app.model.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListPlantResponse {
    private Long id;

    private String name;

    private String country;

    private String date;

    private Long checkups;

    private Long alertsYellow;

    private Long alertsRed;

    private Long sensorsDisable;

    private String createdBy;
}
