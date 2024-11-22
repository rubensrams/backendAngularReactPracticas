package mx.org.angular.react.practicas.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.org.angular.react.practicas.dao.IAvisoDAO;
import mx.org.angular.react.practicas.entity.AvisosEntity;
import mx.org.angular.react.practicas.service.IAvisosService;

@Service
public class AvisosServiceImpl implements IAvisosService{
	
	@Autowired
	private IAvisoDAO avisoDAO;

	@Override
    @Transactional(readOnly = true)
	public List<AvisosEntity> findAll() {
		return (List<AvisosEntity>) avisoDAO.findAll();

	}

    @Transactional(readOnly = true)
    @Override
	public Optional<AvisosEntity> findById(Long id) {
    	 return avisoDAO.findById(id);
	}

    @Transactional
    @Override
	public AvisosEntity save(AvisosEntity user) {
        return avisoDAO.save(user);
	}

    @Transactional
    @Override
	public void deleteById(Long id) {
    	avisoDAO.deleteById(id);
		
	}

}
