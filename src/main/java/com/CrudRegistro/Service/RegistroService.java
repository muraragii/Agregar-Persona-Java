package com.CrudRegistro.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import com.CrudRegistro.CrudPersona.Telefono;
import org.springframework.stereotype.Service;

import com.CrudRegistro.Repository.RegistroRepository;

import com.CrudRegistro.CrudPersona.Persona;

@Service
public class RegistroService {

	private RegistroRepository registroRepository;
	
	
	public RegistroService(RegistroRepository registroRepository) {
		this.registroRepository = registroRepository;
	}
	
	public Persona crearPersona(Persona persona) {
		if (!validarCorreo(persona.getCorreo())) {
			throw new IllegalArgumentException("El correo electrónico no es válido");
		}
		if (!validate(persona.getContrasena(),1,1,2)) {
			throw new IllegalArgumentException("La contraseña debe tener al menos 1 letra mayúscula, 1 letra minúscula y 2 números");
	    }

		return registroRepository.save(persona);
	}

	
	public List<Persona> obtenerTodasLasPersonas(){
		return registroRepository.findAll();
	}
	public Optional<Persona> getPersona(Long id) {
		return registroRepository.findById(id);
	}
	public Optional<Persona> obtenerPersonaPorId(Long id){
		return registroRepository.findById(id);
	}
	
	public Persona actualizarPersona(Persona persona) {
		return registroRepository.save(persona);
	}

	public void eliminarPersona(Long id) {
		registroRepository.deleteById(id);
	}

	public void agregarTelefono(Long personaId, Telefono telefono) {
		Optional<Persona> registroOptional = registroRepository.findById(personaId);
		if (registroOptional.isPresent()) {
			Persona registro = registroOptional.get();

			Persona persona = new Persona();

			boolean correoRegistrado = registroRepository.existsByCorreo(persona.getCorreo());
			if (correoRegistrado) {
				throw new IllegalStateException("El correo ya está registrado");
			}

			telefono.setPersona(registro);
			registro.getTelefonos().add(telefono);
			registroRepository.save(registro);
		}
	}

	public boolean validarCorreo(String correo) {
		String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
		return correo.matches(regex);
	}
		
	public static boolean validate(String password, int minUpperCase, int minLowerCase, int minDigits) {
	    int upperCaseCount = 0;
	    int lowerCaseCount = 0;
	    int digitCount = 0;

	    for (char c : password.toCharArray()) {
	        if (Character.isUpperCase(c)) {
	            upperCaseCount++;
	        } else if (Character.isLowerCase(c)) {
	            lowerCaseCount++;
	        } else if (Character.isDigit(c)) {
	            digitCount++;
	        }
	    }

	    return upperCaseCount >= minUpperCase && lowerCaseCount >= minLowerCase && digitCount >= minDigits;
	}
}
