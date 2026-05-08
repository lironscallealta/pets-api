/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.api_mascotas.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDTO {

    private Long idUsuario;
    private String nombreUsuario;
    /*private String apellidoUsuario;
    private String rut;
    private String dv;
    private String telefono;
    private String email;*/

    // DATOS EN COMENTARIO PARA CORRER EL PROGRAMA RAPIDO
    // SACAR COMENTARIO DE ATRIBUTOS DESPUES
}
