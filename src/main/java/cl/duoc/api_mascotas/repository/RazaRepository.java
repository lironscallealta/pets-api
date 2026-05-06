package cl.duoc.api_mascotas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cl.duoc.api_mascotas.model.Raza;

public interface RazaRepository extends JpaRepository <Raza, Long> {}
