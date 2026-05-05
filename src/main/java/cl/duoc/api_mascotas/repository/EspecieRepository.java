package cl.duoc.api_mascotas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.duoc.api_mascotas.model.Especie;

public interface EspecieRepository extends JpaRepository <Especie, Long>   {}
