/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.api_mascotas.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Datos de una mascota registrada")
public class MascotaResponseDTO {

    @Schema(description = "Identificador único", example = "1")
    private Long id;

    @Schema(description = "Nombre de la mascota", example = "Firulais")
    private String nombreMascota;

    @Schema(description = "Fecha de nacimiento", example = "2020-05-15")
    private LocalDate fechaNacimientoMascota;

    @Schema(description = "Indica si la mascota es dócil", example = "true")
    private Boolean esDocilBoolean;

    @Schema(description = "Información de la raza")
    private RazaResponseDTO razaResponse;

    @Schema(description = "ID del cliente dueño", example = "1")
    private Long idCliente;
}
