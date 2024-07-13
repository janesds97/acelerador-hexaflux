package com.acelerador.HexaFlux.usuario.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * Represents a user (Usuario) in the system.
 */
@Document(collection = "usuarios")
public class Usuario {

    @Id
    private String id;
    private String nombre;
    private String correoElectronico;
    private String contrasena;
    private String rol;
    private LocalDateTime fechaCreacion;
    
    /**
     * Default constructor for Usuario.
     */
    public Usuario() {
	}

    /**
     * Constructs a Usuario with the specified details.
     *
     * @param id the unique identifier of the user
     * @param nombre the name of the user
     * @param correoElectronico the email of the user
     * @param contrasena the password of the user
     * @param rol the role of the user
     * @param fechaCreacion the creation date of the user
     */
	public Usuario(String id, String nombre, String correoElectronico, String contrasena, String rol,
			LocalDateTime fechaCreacion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.correoElectronico = correoElectronico;
		this.contrasena = contrasena;
		this.rol = rol;
		this.fechaCreacion = fechaCreacion;
	}

	// Getters y setters

	/**
     * Gets the unique identifier of the user.
     *
     * @return the unique identifier of the user
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the user.
     *
     * @param id the unique identifier of the user
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the name of the user.
     *
     * @return the name of the user
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the name of the user.
     *
     * @param nombre the name of the user
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Gets the email of the user.
     *
     * @return the email of the user
     */
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    /**
     * Sets the email of the user.
     *
     * @param correoElectronico the email of the user
     */
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    /**
     * Sets the email of the user.
     *
     * @param correoElectronico the email of the user
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Sets the password of the user.
     *
     * @param contrasena the password of the user
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * Gets the role of the user.
     *
     * @return the role of the user
     */
    public String getRol() {
        return rol;
    }

    /**
     * Sets the role of the user.
     *
     * @param rol the role of the user
     */
    public void setRol(String rol) {
        this.rol = rol;
    }

    /**
     * Gets the creation date of the user.
     *
     * @return the creation date of the user
     */
    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * Sets the creation date of the user.
     *
     * @param fechaCreacion the creation date of the user
     */
    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}