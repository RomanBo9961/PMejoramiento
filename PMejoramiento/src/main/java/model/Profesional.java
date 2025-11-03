package model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "profesional")
public class Profesional {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String especialidad;
	private LocalDateTime horarioDisponible;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public LocalDateTime getHorarioDisponible() {
		return horarioDisponible;
	}

	public void setHorarioDisponible(LocalDateTime horarioDisponible) {
		this.horarioDisponible = horarioDisponible;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	// Getters y Setters

}
