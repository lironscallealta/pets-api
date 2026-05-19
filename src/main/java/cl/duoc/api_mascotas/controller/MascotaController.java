/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.api_mascotas.controller;

import cl.duoc.api_mascotas.dto.request.MascotaRequestDTO;
import cl.duoc.api_mascotas.dto.response.MascotaResponseDTO;
import cl.duoc.api_mascotas.service.MascotaService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pets")
@RequiredArgsConstructor
public class MascotaController {

    private final MascotaService mascotaService;

    @PostMapping()
    public ResponseEntity<MascotaResponseDTO> registrarMascota(@Valid @RequestBody MascotaRequestDTO mascotaRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mascotaService.registrarMascota(mascotaRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<MascotaResponseDTO>> consultarMascotaId(@PathVariable Long id) {
        return ResponseEntity.ok(mascotaService.consultarMascotaId(id));
    }

    @GetMapping()
    public ResponseEntity<List<MascotaResponseDTO>> consultarMascotas() {
        return ResponseEntity.ok(mascotaService.consultarMascotas());
    }

    @GetMapping("/getdos")
    public List<MascotaResponseDTO> consultarMascotasdos() {

        List<MascotaResponseDTO> consultarMascotas = mascotaService.consultarMascotasdos();

        return consultarMascotas;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<MascotaResponseDTO>> actualizarMascota(
            @PathVariable Long id, @RequestBody MascotaRequestDTO nuevosDatos) {

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(mascotaService.actualizarMascota(id, nuevosDatos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMascotaId(@PathVariable Long id) {
        mascotaService.eliminarMascotaId(id);
        return ResponseEntity.noContent().build();
    }
}
