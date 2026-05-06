package cl.duoc.api_mascotas.service;

import org.springframework.stereotype.Service;
import cl.duoc.api_mascotas.dto.request.MascotaRequestDTO;
import cl.duoc.api_mascotas.dto.response.EspecieResponseDTO;
import cl.duoc.api_mascotas.dto.response.MascotaResponseDTO;
import cl.duoc.api_mascotas.dto.response.RazaResponseDTO;
import cl.duoc.api_mascotas.dto.response.UsuarioResponseDTO;
import cl.duoc.api_mascotas.model.Especie;
import cl.duoc.api_mascotas.model.Raza;
import cl.duoc.api_mascotas.model.Mascota;
import cl.duoc.api_mascotas.repository.EspecieRepository;
import cl.duoc.api_mascotas.repository.MascotaRepository;
import cl.duoc.api_mascotas.repository.RazaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MascotaService {

    private final MascotaRepository mascotaRepository;
    private final EspecieRepository especieRepository;
    private final RazaRepository razaRepository;

    private MascotaResponseDTO mapToMascotaToMascotaResponse(Mascota mascotaModel) {

        MascotaResponseDTO mascotaResponse = new MascotaResponseDTO();
        EspecieResponseDTO especieResponse = new EspecieResponseDTO();
        RazaResponseDTO razaResponse = new RazaResponseDTO();
        UsuarioResponseDTO usuarioResponse = new UsuarioResponseDTO(); // -> Revisarlo despues aca iria webclient
        // Especie especie = new Especie();

        // Seteando Especie
        especieResponse.setId(mascotaModel.getEspecie().getId());
        especieResponse.setNombreEspecie(mascotaModel.getEspecie().getNombreEspecie()); // EJ PERRO
        especieResponse.setDescripcionEspecie(mascotaModel.getEspecie().getDescripcionEspecie()); // EJ DOMESTICO

        // Seteando Raza
        razaResponse.setId(mascotaModel.getRaza().getId());
        razaResponse.setNombreRaza(mascotaModel.getRaza().getNombreRaza()); // EJ BULLDOG
        razaResponse.setEspecieResponse(especieResponse);

        // Seteando Clase Maestra
        mascotaResponse.setId(mascotaModel.getId());
        mascotaResponse.setNombreMascota(mascotaModel.getNombreMascota()); // Pello
        mascotaResponse.setFechaNacimientoMascota(mascotaModel.getFechaNacimientoMascota());
        mascotaResponse.setEspecieResponse(especieResponse); // Objeto
        mascotaResponse.setRazaResponse(razaResponse); // Objeto
        mascotaResponse.setIdCliente(1L); // este va con client

        return mascotaResponse;

    }

    public MascotaResponseDTO registrarMascota(MascotaRequestDTO mascotaRequest) {

        Especie especieRegistrar = especieRepository.findById(mascotaRequest.getIdEspecie())
                .orElseThrow(() -> new RuntimeException("No existe especie")); // moverlos a clase excepciones
        Raza razaRegistrar = razaRepository.findById(mascotaRequest.getIdRaza())
                .orElseThrow(() -> new RuntimeException("raza no existente")); // excepciones
        // Clientcleinte.findById(mascotaRequest.getIdCliente()).orElseThrow(() -> new
        // RuntimeException("Cliente no existente")); -> esto esta para despues

        Mascota mascotaModel = new Mascota();

        mascotaModel.setNombreMascota(mascotaRequest.getNombreMascota());
        mascotaModel.setFechaNacimientoMascota(mascotaRequest.getFechaNacimientoMascota());
        // mascotaModel.setEspecie(mascotaRequest.);

        return null;
    }

}
/*
 * private Long id;
 * private String nombreMascota;
 * private LocalDate fechaNacimientoMascota;
 * private EspecieResponseDTO especieResponse;
 * private RazaResponseDTO razaResponse;
 * private Long idCliente; // checar esto cuando se haga con el client
 * 
 * }
 */
