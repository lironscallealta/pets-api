package cl.duoc.api_mascotas.dto.request;

import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MascotaRequestDTO {

    @NotBlank
    @Size(min = 2)
    private String nombreMascota;

    @NotNull
    @PastOrPresent
    private LocalDate fechaNacimientoMascota;

    @NotNull
    @Positive
    private Long idEspecie;

    @NotNull
    @Positive
    private Long idRaza;

    @NotNull
    @Positive
    private Long idCliente;

}
