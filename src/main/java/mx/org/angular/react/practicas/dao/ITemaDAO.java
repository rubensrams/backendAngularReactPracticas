package mx.org.angular.react.practicas.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.org.angular.react.practicas.entity.TemaEntity;


@Repository
public interface ITemaDAO extends CrudRepository<TemaEntity, Long>{

	
}
