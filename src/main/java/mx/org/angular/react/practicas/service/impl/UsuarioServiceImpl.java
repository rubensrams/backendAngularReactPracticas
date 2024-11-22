package mx.org.angular.react.practicas.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.org.angular.react.practicas.dao.IUsuarioDAO;
import mx.org.angular.react.practicas.entity.UsuarioEntity;
import mx.org.angular.react.practicas.service.IUsuariosService;

@Service
public class UsuarioServiceImpl implements IUsuariosService{
	
	@Autowired
	private IUsuarioDAO usuarioDAO;

	@Override
    @Transactional(readOnly = true)
	public List<UsuarioEntity> findAll() {
		return (List<UsuarioEntity>) usuarioDAO.findAll();

	}

    @Transactional(readOnly = true)
    @Override
	public Optional<UsuarioEntity> findById(Long id) {
    	 return usuarioDAO.findById(id);
	}

    @Transactional
    @Override
	public UsuarioEntity save(UsuarioEntity user) {
        return usuarioDAO.save(user);
	}

    @Transactional
    @Override
	public void deleteById(Long id) {
    	usuarioDAO.deleteById(id);
		
	}

}
