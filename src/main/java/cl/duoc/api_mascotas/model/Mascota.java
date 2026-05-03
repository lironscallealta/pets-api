package cl.duoc.api_mascotas.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    
    @Column(nullable = false)
    private Long idDueno;

    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimientoMascota;

    @Column(length = 30)
    private Especie espacie;
    // * hacer clasep para tabla

    @Column(length = 30)
    private Raza raza;
   


}
