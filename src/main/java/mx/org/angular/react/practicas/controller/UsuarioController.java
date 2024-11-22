package mx.org.angular.react.practicas.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import mx.org.angular.react.practicas.entity.UsuarioEntity;
import mx.org.angular.react.practicas.service.IUsuariosService;

@RestController
public class UsuarioController {

	@Autowired 
	private IUsuariosService usuariosService;

	
    @GetMapping("/getUsuarios")
    public List<UsuarioEntity> list() {
    	System.out.println("El total de suaurios es: "+ usuariosService.findAll().size());
        return usuariosService.findAll();
    }

    
    @GetMapping("/getUsuario/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        Optional<UsuarioEntity> userOptional = usuariosService.findById(id);
        if (userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(userOptional.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap("error", "el usuario no se encontro por el id:" + id));
    }
    
    @PostMapping("/usuarios/create")
    public ResponseEntity<UsuarioEntity> create(@RequestBody UsuarioEntity user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuariosService.save(user));
    }
    
    
    @PutMapping("/usuarios/actualizar/{id}")
    public ResponseEntity<UsuarioEntity> update(@PathVariable Long id, @RequestBody UsuarioEntity userPost) {
        Optional<UsuarioEntity> userOptional = usuariosService.findById(id);

        if (userOptional.isPresent()) {
        	UsuarioEntity userDb = userOptional.get();
            userDb.setPassword(userPost.getPassword());
            userDb.setNombre(userPost.getNombre());
            userDb.setPaterno(userPost.getPaterno());
            userDb.setMaterno(userPost.getMaterno());
            userDb.setFoto(userPost.getFoto());
            userDb.setSexo(userPost.getSexo());
            userDb.setEstadoCivil(userPost.getEstadoCivil());
            
            return ResponseEntity.ok(usuariosService.save(userDb));
        }
        return ResponseEntity.notFound().build();
    }
    
    
    @DeleteMapping("/usuarios/eliminar/{id}")
    public ResponseEntity<UsuarioEntity> update(@PathVariable Long id) {
        Optional<UsuarioEntity> userOptional = usuariosService.findById(id);

        if (userOptional.isPresent()) {
        	UsuarioEntity userDb = userOptional.get();
            userDb.setEstatus(0);
            return ResponseEntity.ok(usuariosService.save(userDb));
        }
        return ResponseEntity.notFound().build();
    }
    
    
	
}
