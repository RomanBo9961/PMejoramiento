package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import model.Profesional;

public interface ProfesionalRepository extends JpaRepository<Profesional, Integer> {
}
