package com.sistema_de_alertas.Sistema_de_Alertas.app.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlantRequest {

    private String name;

    private String country;

    private Long userId;

}
