package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import model.Servicio;

public interface ServicioRepository extends JpaRepository<Servicio, Integer> {
}
