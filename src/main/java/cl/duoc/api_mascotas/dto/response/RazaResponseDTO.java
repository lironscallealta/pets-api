package cl.duoc.api_mascotas.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RazaResponseDTO {

    Long id;
    EspecieResponseDTO especieResponse;
    String nombreRaza;
    String DescripcionEspecie;

}