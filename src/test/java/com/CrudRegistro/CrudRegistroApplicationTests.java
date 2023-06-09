package com.CrudRegistro;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.CrudRegistro.Service.RegistroService;
import com.CrudRegistro.Repository.RegistroRepository;
import com.CrudRegistro.CrudPersona.Persona;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CrudRegistroApplicationTests {
	
	@Mock
    private RegistroRepository registroRepository;

    private RegistroService registroService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        registroService = new RegistroService(registroRepository);
    }
    
    @Test
    void testAgregarPersona() {
       
    	// Arrange
        Persona persona = new Persona();
        persona.setCorreo("test@example.com");
        persona.setContrasena("Password123");

        when(registroRepository.save(any(Persona.class))).thenReturn(persona);

        // Act
        Persona result = registroService.crearPersona(persona);

        // Assert
        assertNotNull(result);
        assertEquals(persona.getCorreo(), result.getCorreo());
        assertEquals(persona.getContrasena(), result.getContrasena());

        verify(registroRepository, times(1)).save(any(Persona.class));
    }

    @Test
    void testValidarCorreo_CorreoValido() {

        String correo = "test@example.com";
        boolean resultado = registroService.validarCorreo(correo);

        assertTrue(resultado);
    }

    @Test
    public void testCrearPersona_WithInvalidCorreo_ShouldThrowIllegalArgumentException() {
        // Arrange
        Persona persona = new Persona();
        persona.setCorreo("invalidcorreo");
        persona.setContrasena("Password123");

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> registroService.crearPersona(persona));

        verify(registroRepository, never()).save(any(Persona.class));
    }

    @Test
    public void testCrearPersona_WithInvalidContrasena_ShouldThrowIllegalArgumentException() {
        // Arrange
        Persona persona = new Persona();
        persona.setCorreo("test@example.com");
        persona.setContrasena("weakpassword");

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> registroService.crearPersona(persona));

        verify(registroRepository, never()).save(any(Persona.class));
    }

	@Test
	void contextLoads() {
	}

}
