package com.sistema_de_alertas.Sistema_de_Alertas.app.repository;

import com.sistema_de_alertas.Sistema_de_Alertas.app.model.dto.response.ListPlantResponse;
import com.sistema_de_alertas.Sistema_de_Alertas.app.model.entity.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PlantRepository extends JpaRepository<Plant,Long> {

    @Query("SELECT new ListPlantResponse(p.name, p.country, p.checkups, " +
            "SIZE(p.alertsYellow), SIZE(p.alertsRed), SIZE(p.sensorsDisable), u.username) " +
            "FROM Plant p JOIN p.user u")
    List<ListPlantResponse> findAllPlants();

    Optional<Plant> findByName(String name);

}
