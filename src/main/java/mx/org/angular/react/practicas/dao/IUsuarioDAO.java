package mx.org.angular.react.practicas.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.org.angular.react.practicas.entity.UsuarioEntity;


@Repository
public interface IUsuarioDAO extends CrudRepository<UsuarioEntity, Long>{

	
}
