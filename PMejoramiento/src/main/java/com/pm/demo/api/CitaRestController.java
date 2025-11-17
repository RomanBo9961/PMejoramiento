package com.pm.demo.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pm.demo.model.Cita;
import com.pm.demo.repository.CitaRepository;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/citas")
public class CitaRestController {
	private final CitaRepository repo;

	public CitaRestController(CitaRepository repo) {
		this.repo = repo;
	}

	@GetMapping
	public ResponseEntity<List<Cita>> listar() {
		List<Cita> citas = repo.findAll();
		return ResponseEntity.ok(citas);
	}

	@PostMapping
	public ResponseEntity<Cita> crear(@Valid @RequestBody Cita c) {
		Cita saved = repo.save(c);
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cita> obtener(@PathVariable Integer id) {
		return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cita> actualizar(@PathVariable Integer id, @Valid @RequestBody Cita c) {
		if (!repo.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		c.setId(id);
		Cita updated = repo.save(c);
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