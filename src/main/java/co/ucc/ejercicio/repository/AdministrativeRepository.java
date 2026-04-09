package co.ucc.ejercicio.repository;

import co.ucc.ejercicio.model.Administrative;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdministrativeRepository extends JpaRepository<Administrative, Long> {
    Optional<Administrative> findByEmail(String email);
}
