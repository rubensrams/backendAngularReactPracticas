package mx.org.angular.react.practicas.service;

import java.util.List;
import java.util.Optional;
import org.springframework.lang.NonNull;
import mx.org.angular.react.practicas.entity.UsuarioEntity;


public interface IUsuariosService {

    List<UsuarioEntity> findAll();

    Optional<UsuarioEntity> findById(@NonNull Long id);

    UsuarioEntity save(UsuarioEntity user);

    void deleteById(Long id);
	
}
