package api;

import model.Usuario;
import repository.UsuarioRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioRestController {
	private final UsuarioRepository repo;

	public UsuarioRestController(UsuarioRepository repo) {
		this.repo = repo;
	}

	@GetMapping
	public List<Usuario> listar() {
		return repo.findAll();
	}

	@PostMapping
	public Usuario crear(@RequestBody Usuario u) {
		return repo.save(u);
	}

	@GetMapping("/{id}")
	public Usuario obtener(@PathVariable Integer id) {
		return repo.findById(id).orElse(null);
	}

	@PutMapping("/{id}")
	public Usuario actualizar(@PathVariable Integer id, @RequestBody Usuario u) {
		u.setId(id);
		return repo.save(u);
	}

	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable Integer id) {
		repo.deleteById(id);
	}
}
