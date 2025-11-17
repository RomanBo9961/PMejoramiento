package com.pm.demo.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pm.demo.model.Usuario;
import com.pm.demo.repository.UsuarioRepository;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioRestController {
	private final UsuarioRepository repo;

	public UsuarioRestController(UsuarioRepository repo) {
		this.repo = repo;
	}

	@GetMapping
	public ResponseEntity<List<Usuario>> listar() {
		List<Usuario> usuarios = repo.findAll();
		return ResponseEntity.ok(usuarios);
	}

	@PostMapping
	public ResponseEntity<Usuario> crear(@Valid @RequestBody Usuario u) {
		Usuario saved = repo.save(u);
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> obtener(@PathVariable Integer id) {
		return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Usuario> actualizar(@PathVariable Integer id, @Valid @RequestBody Usuario u) {
		if (!repo.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		u.setId(id);
		Usuario updated = repo.save(u);
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