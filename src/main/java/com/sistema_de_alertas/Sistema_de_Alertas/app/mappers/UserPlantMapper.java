package com.sistema_de_alertas.Sistema_de_Alertas.app.mappers;

import com.sistema_de_alertas.Sistema_de_Alertas.app.model.dto.response.UserPlantResponse;
import com.sistema_de_alertas.Sistema_de_Alertas.auth.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserPlantMapper {
    UserPlantResponse toUserPlantResponse(UserEntity user);
}
