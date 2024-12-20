package mx.org.angular.react.practicas.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.org.angular.react.practicas.entity.UsuarioEntity;
import mx.org.angular.react.practicas.service.IUsuariosService;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/api/users")
public class UsuarioController {

	@Autowired 
	private IUsuariosService usuariosService;

	
    @GetMapping("/getUsers")
    public List<UsuarioEntity> list() {
    	System.out.println("El total de suaurios es: "+ usuariosService.findAll().size());
        return usuariosService.findAll();
    }

    
    @GetMapping("/getUser/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        Optional<UsuarioEntity> userOptional = usuariosService.findById(id);
        if (userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(userOptional.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap("error", "el usuario no se encontro por el id:" + id));
    }
    
    @PostMapping("/create")
    public ResponseEntity<UsuarioEntity> create(@RequestBody UsuarioEntity user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuariosService.save(user));
    }
    
    
    @PutMapping("/update/{id}")
    public ResponseEntity<UsuarioEntity> update(@PathVariable Long id, @RequestBody UsuarioEntity userPost) {
        
    	Optional<UsuarioEntity> mod= usuariosService.update(userPost, id);
    	
    	if (mod.isPresent()) {
            return ResponseEntity.ok(mod.orElseThrow());
        }
        
        return ResponseEntity.notFound().build();
    }
    
    
    @DeleteMapping("/delete/{id}")
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
