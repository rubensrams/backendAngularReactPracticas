package mx.org.angular.react.practicas.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.org.angular.react.practicas.entity.RolEntity;


@Repository
public interface IRolDAO extends CrudRepository<RolEntity, Long>{

	
}
