/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.api_mascotas.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Datos para registrar o actualizar una mascota")
public class MascotaRequestDTO {

    @NotBlank
    @Size(min = 2)
    @Schema(description = "Nombre de la mascota", example = "Firulais", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nombreMascota;

    @NotNull
    @PastOrPresent
    @Schema(description = "Fecha de nacimiento", example = "2020-05-15", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDate fechaNacimientoMascota;

    @NotNull
    @Schema(
            description = "Indica si la mascota es dócil",
            example = "true",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean esDocilBoolean;

    @NotNull
    @Positive
    @Schema(description = "ID de la raza", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long idRaza;

    @NotNull
    @Positive
    @Schema(
            description = "ID del cliente dueño en users-api",
            example = "1",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private Long idCliente;
}
