package cl.duoc.api_mascotas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.duoc.api_mascotas.model.Mascota;

public interface MascotaRepository extends JpaRepository <Mascota , Long> {

}
