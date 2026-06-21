/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.api_mascotas.controller;

import cl.duoc.api_mascotas.dto.DtoApiError;
import cl.duoc.api_mascotas.dto.request.MascotaRequestDTO;
import cl.duoc.api_mascotas.dto.response.MascotaResponseDTO;
import cl.duoc.api_mascotas.service.MascotaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @ApiResponses({
        @ApiResponse(
                responseCode = "201",
                description = "Mascota registrada correctamente",
                content =
                        @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = MascotaResponseDTO.class))),
        @ApiResponse(
                responseCode = "400",
                description = "Datos de entrada inválidos",
                content = @Content(schema = @Schema(implementation = DtoApiError.class))),
        @ApiResponse(
                responseCode = "401",
                description = "Token JWT ausente o inválido",
                content = @Content(schema = @Schema(implementation = DtoApiError.class))),
        @ApiResponse(
                responseCode = "500",
                description = "Error interno del servidor",
                content = @Content(schema = @Schema(implementation = DtoApiError.class)))
    })
    public ResponseEntity<MascotaResponseDTO> registrarMascota(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                            description = "Datos de la mascota a registrar",
                            required = true,
                            content =
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = MascotaRequestDTO.class),
                                            examples = @ExampleObject(name = "Ejemplo de mascota", value = """
                                                                    {
                                                                      "nombreMascota": "Firulais",
                                                                      "fechaNacimientoMascota": "2020-05-15",
                                                                      "esDocilBoolean": true,
                                                                      "idRaza": 1,
                                                                      "idCliente": 1
                                                                    }
                                                                    """)))
                    @Valid
                    @RequestBody
                    MascotaRequestDTO mascotaRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mascotaService.registrarMascota(mascotaRequest));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consultar mascota por ID", description = "Obtiene una mascota usando su identificador único.")
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Mascota encontrada",
                content =
                        @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = MascotaResponseDTO.class))),
        @ApiResponse(
                responseCode = "404",
                description = "Mascota no encontrada",
                content = @Content(schema = @Schema(implementation = DtoApiError.class))),
        @ApiResponse(
                responseCode = "401",
                description = "Token JWT ausente o inválido",
                content = @Content(schema = @Schema(implementation = DtoApiError.class)))
    })
    public ResponseEntity<Optional<MascotaResponseDTO>> consultarMascotaId(@PathVariable Long id) {
        return ResponseEntity.ok(mascotaService.consultarMascotaId(id));
    }

    @GetMapping()
    @Operation(summary = "Listar mascotas", description = "Retorna todas las mascotas registradas.")
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Listado obtenido correctamente",
                content = @Content(mediaType = "application/json")),
        @ApiResponse(
                responseCode = "401",
                description = "Token JWT ausente o inválido",
                content = @Content(schema = @Schema(implementation = DtoApiError.class)))
    })
    public ResponseEntity<List<MascotaResponseDTO>> consultarMascotas() {
        return ResponseEntity.ok(mascotaService.consultarMascotas());
    }

    @GetMapping("/getdos")
    @Operation(
            summary = "Listar mascotas (alternativo)",
            description = "Lista mascotas usando un mapeo alternativo en el servicio.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente"),
        @ApiResponse(
                responseCode = "401",
                description = "Token JWT ausente o inválido",
                content = @Content(schema = @Schema(implementation = DtoApiError.class)))
    })
    public List<MascotaResponseDTO> consultarMascotasdos() {
        return mascotaService.consultarMascotasdos();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar mascota", description = "Actualiza los datos de una mascota existente.")
    @ApiResponses({
        @ApiResponse(
                responseCode = "202",
                description = "Mascota actualizada",
                content =
                        @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = MascotaResponseDTO.class))),
        @ApiResponse(
                responseCode = "400",
                description = "Datos de entrada inválidos",
                content = @Content(schema = @Schema(implementation = DtoApiError.class))),
        @ApiResponse(
                responseCode = "404",
                description = "Mascota no encontrada",
                content = @Content(schema = @Schema(implementation = DtoApiError.class))),
        @ApiResponse(
                responseCode = "401",
                description = "Token JWT ausente o inválido",
                content = @Content(schema = @Schema(implementation = DtoApiError.class)))
    })
    public ResponseEntity<Optional<MascotaResponseDTO>> actualizarMascota(
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                            description = "Nuevos datos de la mascota",
                            required = true,
                            content =
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = MascotaRequestDTO.class),
                                            examples = @ExampleObject(name = "Ejemplo de actualización", value = """
                                                                    {
                                                                      "nombreMascota": "Firulais Actualizado",
                                                                      "fechaNacimientoMascota": "2020-05-15",
                                                                      "esDocilBoolean": false,
                                                                      "idRaza": 2,
                                                                      "idCliente": 1
                                                                    }
                                                                    """)))
                    @RequestBody
                    MascotaRequestDTO nuevosDatos) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(mascotaService.actualizarMascota(id, nuevosDatos));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar mascota", description = "Elimina una mascota por su identificador.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Mascota eliminada correctamente"),
        @ApiResponse(
                responseCode = "404",
                description = "Mascota no encontrada",
                content = @Content(schema = @Schema(implementation = DtoApiError.class))),
        @ApiResponse(
                responseCode = "401",
                description = "Token JWT ausente o inválido",
                content = @Content(schema = @Schema(implementation = DtoApiError.class)))
    })
    public ResponseEntity<Void> eliminarMascotaId(@PathVariable Long id) {
        mascotaService.eliminarMascotaId(id);
        return ResponseEntity.noContent().build();
    }
}
