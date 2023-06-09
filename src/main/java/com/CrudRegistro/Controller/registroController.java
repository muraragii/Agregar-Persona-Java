package com.CrudRegistro.Controller;

import java.util.List;

import java.util.Optional;

import com.CrudRegistro.CrudPersona.Telefono;
import com.CrudRegistro.Repository.RegistroRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CrudRegistro.Service.RegistroService;

import com.CrudRegistro.CrudPersona.Persona;

@RestController
@RequestMapping("/personas")
public class registroController {
	
	private final RegistroService registroService;
	
	public registroController(RegistroService registroService) {
		this.registroService = registroService;
	}

	@PostMapping
	public ResponseEntity<?> crearPersona(@RequestBody Persona persona) {
	    
	        Persona nuevaPersona = registroService.crearPersona(persona);
	        ResponseEntity<Persona> respuesta = ResponseEntity.status(HttpStatus.CREATED).body(nuevaPersona);
	        return respuesta;
	        
	}

	



	@GetMapping
    public ResponseEntity<List<Persona>> obtenerTodasLasPersonas() {
        List<Persona> personas = registroService.obtenerTodasLasPersonas();
        return ResponseEntity.ok(personas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Persona> obtenerPersonaPorId(@PathVariable Long id) {
        Optional<Persona> persona = registroService.obtenerPersonaPorId(id);
        return persona.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Persona> actualizarPersona(@PathVariable Long id, @RequestBody Persona personaActualizada) {
        Optional<Persona> personaExistente = registroService.obtenerPersonaPorId(id);
        if (personaExistente.isPresent()) {
            personaActualizada.setId(id);
            Persona personaActualizadaDb = registroService.actualizarPersona(personaActualizada);
            return ResponseEntity.ok(personaActualizadaDb);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPersona(@PathVariable Long id) {
        Optional<Persona> personaExistente = registroService.obtenerPersonaPorId(id);
        if (personaExistente.isPresent()) {
            registroService.eliminarPersona(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @PostMapping("/{personaId}/telefonos")
    public ResponseEntity<String> agregarTelefono(@PathVariable Long personaId, @RequestBody Telefono telefono) {
        registroService.agregarTelefono(personaId, telefono);
        return ResponseEntity.ok("Teléfono agregado correctamente");
    }
}
