package cl.duoc.api_mascotas.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "razas")
public class Raza {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long id;
    String nombreRaza;
    
}
