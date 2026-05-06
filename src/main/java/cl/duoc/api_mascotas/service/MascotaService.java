package cl.duoc.api_mascotas.service;

import org.springframework.stereotype.Service;
import cl.duoc.api_mascotas.dto.request.MascotaRequestDTO;
import cl.duoc.api_mascotas.dto.response.EspecieResponseDTO;
import cl.duoc.api_mascotas.dto.response.MascotaResponseDTO;
import cl.duoc.api_mascotas.dto.response.RazaResponseDTO;
//import cl.duoc.api_mascotas.dto.response.UsuarioResponseDTO;
import cl.duoc.api_mascotas.model.Mascota;
import cl.duoc.api_mascotas.model.Raza;
//import cl.duoc.api_mascotas.repository.EspecieRepository;
import cl.duoc.api_mascotas.repository.MascotaRepository;
import cl.duoc.api_mascotas.repository.RazaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MascotaService {

    private final MascotaRepository mascotaRepository;    
    private final RazaRepository razaRepository;
    // private final EspecieRepository especieRepository;

    private MascotaResponseDTO mapToMascotaToMascotaResponse(Mascota mascotaModel) {

        MascotaResponseDTO mascotaResponse = new MascotaResponseDTO();
        EspecieResponseDTO especieResponse = new EspecieResponseDTO();
        RazaResponseDTO razaResponse = new RazaResponseDTO();
        // UsuarioResponseDTO usuarioResponse = new UsuarioResponseDTO(); // ->
        // Revisarlo despues aca iria webclient
        // Especie especie = new Especie();

        // Seteando Especie
        especieResponse.setId(mascotaModel.getRaza().getEspecie().getId());
        especieResponse.setNombreEspecie(mascotaModel.getRaza().getEspecie().getNombreEspecie()); // EJ PERRO
        especieResponse.setDescripcionEspecie(mascotaModel.getRaza().getEspecie().getDescripcionEspecie()); // -> abajo
        especieResponse.setExoticaBoolean(mascotaModel.getRaza().getEspecie().getExoticaBoolean()); // Si fuese iguana
                                                                                                    // poner true por
        // EJ Mamífero carnívoro subespecie del lobo, animal domestico por excelencia.

        // Seteando Raza
        razaResponse.setId(mascotaModel.getRaza().getId());
        razaResponse.setNombreRaza(mascotaModel.getRaza().getNombreRaza()); // EJ BULLDOG
        razaResponse.setDescripcionRaza(mascotaModel.getRaza().getDescripcionRaza()); // Ej Complexión robusta y
                                                                                      // compacta originaria del Reino
                                                                                      // Unido.
        razaResponse.setEspecieResponse(especieResponse);

        // Seteando Clase Maestra
        mascotaResponse.setId(mascotaModel.getId());
        mascotaResponse.setNombreMascota(mascotaModel.getNombreMascota()); // Pello
        mascotaResponse.setFechaNacimientoMascota(mascotaModel.getFechaNacimientoMascota());
        mascotaResponse.setEsDocilBoolean(mascotaModel.getEsDocilBoolean()); // informacion para saber si es agresivo o
                                                                             // no importante si es que cambian doctor
                                                                             // por ejm
        mascotaResponse.setRazaResponse(razaResponse); // Objeto
        mascotaResponse.setIdCliente(null); // este va con client

        return mascotaResponse;

    }

    public MascotaResponseDTO registrarMascota(MascotaRequestDTO mascotaRequest) {

        Raza razaRegistrar = razaRepository.findById(mascotaRequest.getIdRaza())
                .orElseThrow(() -> new RuntimeException("raza no existente"));

        // Especie especieRegistrar = razaRegistrar.getEspecie(); // podemos omitir esto
        // ya que raza y especie van ligadas, pero para entender mejor el codigo esta
        // asi
        Mascota mascotaModel = new Mascota(); // para mapearlo depsues

        mascotaModel.setNombreMascota(mascotaRequest.getNombreMascota());
        mascotaModel.setFechaNacimientoMascota(mascotaRequest.getFechaNacimientoMascota());
        mascotaModel.setEsDocilBoolean(mascotaRequest.getEsDocilBoolean());
        mascotaModel.setRaza(razaRegistrar);
        mascotaModel.setIdCliente(null);

        mascotaRepository.save(mascotaModel);

        MascotaResponseDTO mascotaResponse = mapToMascotaToMascotaResponse(mascotaModel);

        return mascotaResponse;
    }

}
