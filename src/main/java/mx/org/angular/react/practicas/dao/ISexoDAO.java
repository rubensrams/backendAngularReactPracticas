package mx.org.angular.react.practicas.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.org.angular.react.practicas.entity.SexoEntity;


@Repository
public interface ISexoDAO extends CrudRepository<SexoEntity, Long>{

	
}
