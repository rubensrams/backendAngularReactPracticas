package mx.org.angular.react.practicas.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.org.angular.react.practicas.dao.IUsuarioDAO;
import mx.org.angular.react.practicas.entity.UsuarioEntity;
import mx.org.angular.react.practicas.service.IUsuariosService;

@Service
public class UsuarioServiceImpl implements IUsuariosService, UserDetailsService{
	
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

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UsuarioEntity> optionalUser = usuarioDAO.findByUsername(username);

        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException(String.format("Username %s no existe en el sistema", username));
        }

        UsuarioEntity user = optionalUser.orElseThrow();

        List<GrantedAuthority> authorities = user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getDescripcion()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(username,
                user.getPassword(),
                user.getEstatus()==1,
                true,
                true,
                true,
                authorities);

    }

}
