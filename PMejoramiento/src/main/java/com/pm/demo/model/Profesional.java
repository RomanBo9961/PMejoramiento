package com.pm.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

@Entity
@Table(name = "profesional")
public class Profesional {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; 
	private String nombre;
    private String email;
    private String telefono;
    private String especialidad;
    private Integer experiencia;
    private String estado;
    private String descripcion;
    private LocalDateTime horarioDisponible;
    
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    
    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
    
    public Integer getExperiencia() { return experiencia; }
    public void setExperiencia(Integer experiencia) { this.experiencia = experiencia; }
    
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    
    public LocalDateTime getHorarioDisponible() { return horarioDisponible; }
    public void setHorarioDisponible(LocalDateTime horarioDisponible) { this.horarioDisponible = horarioDisponible; }
    
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}


	


