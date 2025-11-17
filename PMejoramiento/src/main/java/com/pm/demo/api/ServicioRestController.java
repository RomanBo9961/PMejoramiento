package com.pm.demo.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pm.demo.model.Servicio;
import com.pm.demo.repository.ServicioRepository;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/servicios")
public class ServicioRestController {
	private final ServicioRepository repo;

	public ServicioRestController(ServicioRepository repo) {
		this.repo = repo;
	}

	@GetMapping
	public ResponseEntity<List<Servicio>> listar() {
		List<Servicio> servicios = repo.findAll();
		return ResponseEntity.ok(servicios);
	}

	@PostMapping
	public ResponseEntity<Servicio> crear(@Valid @RequestBody Servicio s) {
		Servicio saved = repo.save(s);
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Servicio> obtener(@PathVariable Integer id) {
		return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Servicio> actualizar(@PathVariable Integer id, @Valid @RequestBody Servicio s) {
		if (!repo.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		s.setId(id);
		Servicio updated = repo.save(s);
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