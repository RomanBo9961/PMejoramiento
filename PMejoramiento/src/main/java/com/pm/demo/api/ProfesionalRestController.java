package com.pm.demo.api;

import org.springframework.web.bind.annotation.*;

import com.pm.demo.model.Profesional;
import com.pm.demo.repository.ProfesionalRepository;

import java.util.List;

@RestController
@RequestMapping("/api/profesionales")
public class ProfesionalRestController {
	private final ProfesionalRepository repo;

	public ProfesionalRestController(ProfesionalRepository repo) {
		this.repo = repo;
	}

	@GetMapping
	public List<Profesional> listar() {
		return repo.findAll();
	}

	@PostMapping
	public Profesional crear(@RequestBody Profesional p) {
		return repo.save(p);
	}

	@GetMapping("/{id}")
	public Profesional obtener(@PathVariable Integer id) {
		return repo.findById(id).orElse(null);
	}

	@PutMapping("/{id}")
	public Profesional actualizar(@PathVariable Integer id, @RequestBody Profesional p) {
		p.setId(id);
		return repo.save(p);
	}

	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable Integer id) {
		repo.deleteById(id);
	}
}
