package mx.org.angular.react.practicas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;

import mx.org.angular.react.practicas.entity.AvisosEntity;



public interface IAvisosService {

    List<AvisosEntity> findAll();

    Optional<AvisosEntity> findById(@NonNull Long id);

    AvisosEntity save(AvisosEntity user);

    void deleteById(Long id);
	
}
