package com.pm.demo.api;

import org.springframework.web.bind.annotation.*;

import com.pm.demo.model.Servicio;
import com.pm.demo.repository.ServicioRepository;

import java.util.List;

@RestController
@RequestMapping("/api/servicios")
public class ServicioRestController {
	private final ServicioRepository repo;

	public ServicioRestController(ServicioRepository repo) {
		this.repo = repo;
	}

	@GetMapping
	public List<Servicio> listar() {
		return repo.findAll();
	}

	@PostMapping
	public Servicio crear(@RequestBody Servicio s) {
		return repo.save(s);
	}

	@GetMapping("/{id}")
	public Servicio obtener(@PathVariable Integer id) {
		return repo.findById(id).orElse(null);
	}

	@PutMapping("/{id}")
	public Servicio actualizar(@PathVariable Integer id, @RequestBody Servicio s) {
		s.setId(id);
		return repo.save(s);
	}

	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable Integer id) {
		repo.deleteById(id);
	}
}
