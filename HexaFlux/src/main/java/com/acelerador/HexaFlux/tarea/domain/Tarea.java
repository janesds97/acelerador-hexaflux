package com.acelerador.HexaFlux.tarea.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * Represents a task (Tarea) in the system.
 */
@Document(collection = "tareas")
public class Tarea {

    @Id
    private String id;
    private String nombre;
    private String descripcion;
    private String estado;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaVencimiento;
    private String responsableId;

    /**
     * Default constructor for Tarea.
     */
    public Tarea() {
	}
    
    /**
     * Constructs a Tarea with the specified details.
     *
     * @param id the unique identifier of the task
     * @param nombre the name of the task
     * @param descripcion the description of the task
     * @param estado the current state of the task
     * @param fechaCreacion the creation date of the task
     * @param fechaVencimiento the due date of the task
     * @param responsableId the identifier of the person responsible for the task
     */
	public Tarea(String id, String nombre, String descripcion, String estado, LocalDateTime fechaCreacion,
			LocalDateTime fechaVencimiento, String responsableId) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.estado = estado;
		this.fechaCreacion = fechaCreacion;
		this.fechaVencimiento = fechaVencimiento;
		this.responsableId = responsableId;
	}

	// Getters y setters

	/**
     * Gets the unique identifier of the task.
     *
     * @return the unique identifier of the task
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the task.
     *
     * @param id the unique identifier of the task
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the name of the task.
     *
     * @return the name of the task
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Sets the name of the task.
     *
     * @param nombre the name of the task
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Gets the description of the task.
     *
     * @return the description of the task
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Sets the description of the task.
     *
     * @param descripcion the description of the task
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Gets the current state of the task.
     *
     * @return the current state of the task
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Sets the current state of the task.
     *
     * @param estado the current state of the task
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Gets the creation date of the task.
     *
     * @return the creation date of the task
     */
    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * Sets the creation date of the task.
     *
     * @param fechaCreacion the creation date of the task
     */
    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the due date of the task.
     *
     * @return the due date of the task
     */
    public LocalDateTime getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * Sets the due date of the task.
     *
     * @param fechaVencimiento the due date of the task
     */
    public void setFechaVencimiento(LocalDateTime fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    /**
     * Gets the identifier of the person responsible for the task.
     *
     * @return the identifier of the person responsible for the task
     */
    public String getResponsableId() {
        return responsableId;
    }

    /**
     * Sets the identifier of the person responsible for the task.
     *
     * @param responsableId the identifier of the person responsible for the task
     */
    public void setResponsableId(String responsableId) {
        this.responsableId = responsableId;
    }
}