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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Mascotas", description = "Operaciones CRUD del dominio mascotas.")
public class MascotaController {

    private final MascotaService mascotaService;

    @PostMapping()
    @Operation(summary = "Registrar mascota", description = "Crea un nuevo registro de mascota en el sistema.")
    public ResponseEntity<MascotaResponseDTO> registrarMascota(@Valid @RequestBody MascotaRequestDTO mascotaRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mascotaService.registrarMascota(mascotaRequest));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consultar mascota por ID", description = "Obtiene una mascota usando su identificador único.")
    public ResponseEntity<Optional<MascotaResponseDTO>> consultarMascotaId(@PathVariable Long id) {
        return ResponseEntity.ok(mascotaService.consultarMascotaId(id));
    }

    @GetMapping()
    @Operation(summary = "Listar mascotas", description = "Retorna todas las mascotas registradas.")
    public ResponseEntity<List<MascotaResponseDTO>> consultarMascotas() {
        return ResponseEntity.ok(mascotaService.consultarMascotas());
    }

    @GetMapping("/getdos")
    @Operation(
            summary = "Listar mascotas (alternativo)",
            description = "Lista mascotas usando un mapeo alternativo en el servicio.")
    public List<MascotaResponseDTO> consultarMascotasdos() {
        return mascotaService.consultarMascotasdos();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar mascota", description = "Actualiza los datos de una mascota existente.")
    public ResponseEntity<Optional<MascotaResponseDTO>> actualizarMascota(
            @PathVariable Long id, @RequestBody MascotaRequestDTO nuevosDatos) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(mascotaService.actualizarMascota(id, nuevosDatos));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar mascota", description = "Elimina una mascota por su identificador.")
    public ResponseEntity<Void> eliminarMascotaId(@PathVariable Long id) {
        mascotaService.eliminarMascotaId(id);
        return ResponseEntity.noContent().build();
    }
}
