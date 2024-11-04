package com.sistema_de_alertas.Sistema_de_Alertas.app.model.dto.response;

import lombok.*;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlantResponse {

    private Long id;

    private String name;

    private String country;

    private ZonedDateTime date;

    private Long checkups;

    private List<AlertYellowResponse> alertsYellow;

    private List<AlertRedResponse> alertsRed;

    private List<SensorsDisableResponse> sensorsDisable;

    private UserPlantResponse user;
}
