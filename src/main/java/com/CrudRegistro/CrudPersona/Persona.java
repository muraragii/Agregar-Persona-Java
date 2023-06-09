package com.CrudRegistro.CrudPersona;

//import java.time.LocalDateTime;
//import java.util.Date;                         
import java.util.List;
import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "personas")
@Data
public class Persona {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_persona;
	
	@Column(nullable = false)
	private String nombre;
	
	@Column(nullable = false,unique = true)
	private String correo;
	
	@Column(nullable = false)
	private String contrasena;

	@OneToMany(mappedBy = "persona", cascade = CascadeType.ALL)
	private List<Telefono> telefonos;


	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp  created;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp modified;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp lastLogin;


	@Column(nullable = false)
	private String token;

	@Column(nullable = false)
	private Boolean isActive;

	@Override
	public String toString() {
		return "Persona [id=" + id_persona + ", nombre=" + nombre + ", correo=" + correo + ", contrase�a=" + contrasena
				+ ", telefonos=" + telefonos + ", created=" + created + ", modified=" + modified + ", lastLogin="
				+ lastLogin + ", token=" + token + ", isActive=" + isActive + "]";
	}
	@PrePersist
	public void prePersist(){
		lastLogin = new Timestamp(System.currentTimeMillis());
		modified = new Timestamp(System.currentTimeMillis());
		created = new Timestamp(System.currentTimeMillis());
	}

	public void agregarTelefono(Telefono telefono) {
		telefonos.add(telefono);
		telefono.setPersona(this);
	}
	public Long getId() {
		return id_persona;
	}

	public void setId(Long id) {
		this.id_persona = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public List<Telefono> getTelefonos() {
		return telefonos;
	}

	public void setTelefonos(List<Telefono> telefonos) {
		this.telefonos = telefonos;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public Timestamp getModified() {
		return modified;
	}

	public void setModified(Timestamp modified) {
		this.modified = modified;
	}

	public Timestamp getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}



	
}
