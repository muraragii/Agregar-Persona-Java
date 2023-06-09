package com.CrudRegistro.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CrudRegistro.CrudPersona.Persona;

public interface RegistroRepository extends JpaRepository<Persona,Long> {
    boolean existsByCorreo(String correo);

}
