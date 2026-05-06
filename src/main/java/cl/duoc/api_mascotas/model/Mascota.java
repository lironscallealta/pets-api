package cl.duoc.api_mascotas.model;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mascotas")
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_mascota", length = 50, nullable = false)
    private String nombreMascota;

    @Column(name = "fecha_nacimiento_mascota", nullable = false)
    private LocalDate fechaNacimientoMascota;

    @Column(name = "es_docil_boolean") 
    private Boolean esDocilBoolean;

    @ManyToOne
    @JoinColumn(nullable = false, name = "id_raza")
    private Raza raza;

    @Column(name = "id_cliente")    
    private Long idCliente; // ANOTACION PERSONAL:MODIFICAR A CLIENTE CLIENTE

}
