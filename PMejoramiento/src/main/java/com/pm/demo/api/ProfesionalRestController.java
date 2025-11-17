package com.pm.demo.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pm.demo.model.Profesional;
import com.pm.demo.repository.ProfesionalRepository;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/profesionales")
public class ProfesionalRestController {
	private final ProfesionalRepository repo;

	public ProfesionalRestController(ProfesionalRepository repo) {
		this.repo = repo;
	}

	@GetMapping
	public ResponseEntity<List<Profesional>> listar() {
		List<Profesional> profesionales = repo.findAll();
		return ResponseEntity.ok(profesionales);
	}

	@PostMapping
	public ResponseEntity<Profesional> crear(@Valid @RequestBody Profesional p) {
		Profesional saved = repo.save(p);
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Profesional> obtener(@PathVariable Integer id) {
		return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Profesional> actualizar(@PathVariable Integer id, @Valid @RequestBody Profesional p) {
		if (!repo.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		p.setId(id);
		Profesional updated = repo.save(p);
		return ResponseEntity.ok(updated);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
		if (!repo.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		repo.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}