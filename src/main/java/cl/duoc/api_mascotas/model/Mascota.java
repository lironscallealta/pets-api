package cl.duoc.api_mascotas.model;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String nombreMascota;

    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimientoMascota;

    @ManyToOne
    @JoinColumn(nullable = false, name = "id_especies")
    private Especie especie;

    @ManyToOne
    @JoinColumn(name = "id_razas")
    private Raza raza;

    @Column(nullable = false)
    private Long idCliente; // ANOTACION PERSONAL:MODIFICAR A CLIENTE CLIENTE

}
