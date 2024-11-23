package mx.org.angular.react.practicas.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.org.angular.react.practicas.entity.UsuarioEntity;


@Repository
public interface IUsuarioDAO extends CrudRepository<UsuarioEntity, Long>{

	Optional<UsuarioEntity> findByUsername(String name);
}
