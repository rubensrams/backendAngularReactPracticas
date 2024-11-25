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

import mx.org.angular.react.practicas.entity.AvisosEntity;
import mx.org.angular.react.practicas.service.IAvisosService;
@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/api/advice")
public class AvisosController {

	@Autowired
	private IAvisosService avisosService;

	
    @GetMapping("/getAdvices")
    public List<AvisosEntity> list() {
        return avisosService.findAll();
    }

    
    @GetMapping("/getAdvice/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        Optional<AvisosEntity> userOptional = avisosService.findById(id);
        if (userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(userOptional.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap("error", "Aviso no encontrado por el id:" + id));
    }
    
    @PostMapping("/create")
    public ResponseEntity<AvisosEntity> create(@RequestBody AvisosEntity user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(avisosService.save(user));
    }
    
    
    @PutMapping("/update/{id}")
    public ResponseEntity<AvisosEntity> update(@PathVariable Long id, @RequestBody AvisosEntity avisoPost) {
        Optional<AvisosEntity> userOptional = avisosService.findById(id);

        if (userOptional.isPresent()) {
        	AvisosEntity userDb = userOptional.get();
            userDb.setDescripcion(avisoPost.getDescripcion());
            userDb.setFecha(avisoPost.getFecha());
            userDb.setTema(avisoPost.getTema());
            userDb.setUsuario(avisoPost.getUsuario());
            return ResponseEntity.ok(avisosService.save(userDb));
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<AvisosEntity> eliminar(@PathVariable Long id) {
        Optional<AvisosEntity> userOptional = avisosService.findById(id);

        if (userOptional.isPresent()) {
        	avisosService.deleteById(id);
            return  ResponseEntity.status(HttpStatus.OK).body(userOptional.get());
        }
        return ResponseEntity.notFound().build();
    }
    
	
}
