package com.sistema_de_alertas.Sistema_de_Alertas.auth.repository;

import com.sistema_de_alertas.Sistema_de_Alertas.auth.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

     Optional<UserEntity> findByMail(String mail);
}
