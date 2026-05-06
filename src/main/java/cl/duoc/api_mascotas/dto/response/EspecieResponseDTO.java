package cl.duoc.api_mascotas.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EspecieResponseDTO {

    private Long id;
    private String nombreEspecie;
    private String descripcionEspecie;

}
