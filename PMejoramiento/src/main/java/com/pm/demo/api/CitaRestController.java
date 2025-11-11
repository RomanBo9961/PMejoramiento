package com.pm.demo.api;

import org.springframework.web.bind.annotation.*;

import com.pm.demo.model.Cita;
import com.pm.demo.repository.CitaRepository;

import java.util.List;

@RestController
@RequestMapping("/api/citas")
public class CitaRestController {
	private final CitaRepository repo;

	public CitaRestController(CitaRepository repo) {
		this.repo = repo;
	}

	@GetMapping
	public List<Cita> listar() {
		return repo.findAll();
	}

	@PostMapping
	public Cita crear(@RequestBody Cita c) {
		return repo.save(c);
	}

	@GetMapping("/{id}")
	public Cita obtener(@PathVariable Integer id) {
		return repo.findById(id).orElse(null);
	}

	@PutMapping("/{id}")
	public Cita actualizar(@PathVariable Integer id, @RequestBody Cita c) {
		c.setId(id);
		return repo.save(c);
	}

	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable Integer id) {
		repo.deleteById(id);
	}
}
