/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.api_mascotas.service;

import cl.duoc.api_mascotas.dto.request.MascotaRequestDTO;
import cl.duoc.api_mascotas.dto.response.EspecieResponseDTO;
import cl.duoc.api_mascotas.dto.response.MascotaResponseDTO;
import cl.duoc.api_mascotas.dto.response.RazaResponseDTO;
// import cl.duoc.api_mascotas.dto.response.UsuarioResponseDTO;
import cl.duoc.api_mascotas.model.Mascota;
import cl.duoc.api_mascotas.model.Raza;
// import cl.duoc.api_mascotas.repository.EspecieRepository;
import cl.duoc.api_mascotas.repository.MascotaRepository;
import cl.duoc.api_mascotas.repository.RazaRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        especieResponse.setDescripcionEspecie(
                mascotaModel.getRaza().getEspecie().getDescripcionEspecie()); // -> abajo
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

        Raza razaRegistrar = razaRepository
                .findById(mascotaRequest.getIdRaza())
                .orElseThrow(() -> new RuntimeException("raza no existente"));

        Mascota mascotaModel = new Mascota();

        mascotaModel.setNombreMascota(mascotaRequest.getNombreMascota());
        mascotaModel.setFechaNacimientoMascota(mascotaRequest.getFechaNacimientoMascota());
        mascotaModel.setEsDocilBoolean(mascotaRequest.getEsDocilBoolean());
        mascotaModel.setRaza(razaRegistrar);
        mascotaModel.setIdCliente(mascotaRequest.getIdCliente());

        mascotaRepository.save(mascotaModel);

        MascotaResponseDTO mascotaResponse = mapToMascotaToMascotaResponse(mascotaModel);

        return mascotaResponse;
    }

    public Optional<MascotaResponseDTO> consultarMascotaId(Long idMascota) {

        Mascota mascotaEncontrarId = mascotaRepository.findById(idMascota).orElseThrow(); // verlo despues con los
        // errores
        MascotaResponseDTO mascotaResponse = mapToMascotaToMascotaResponse(mascotaEncontrarId);
        return Optional.of(mascotaResponse);
    }

    public List<MascotaResponseDTO> consultarMascotas() {

        List<Mascota> mascotaEncontrar = mascotaRepository.findAll();
        return mascotaEncontrar.stream()
                .map(this::mapToMascotaToMascotaResponse)
                .toList();
        // el map se podria pasar asi tambien, para probar en otro microservicio .map(m
        // -> this.mapToMascotaToMascotaResponse(m)) m seria el objeto

    }

    // Metodo hecho de la forma que siempre he usado
    public List<MascotaResponseDTO> consultarMascotasdos() {

        List<MascotaResponseDTO> mascotaResponseList = new ArrayList<>();

        List<Mascota> mascotaEncontrar = mascotaRepository.findAll();

        for (Mascota mascota : mascotaEncontrar) {
            MascotaResponseDTO mascotaResponse = mapToMascotaToMascotaResponse(mascota);
            mascotaResponseList.add(mascotaResponse);
        }
        return mascotaResponseList;
    }
    /*
        public Optional<MascotaResponseDTO> eliminarMascotaId(Long idMascota) {
            Optional<Mascota> mascotaEliminar = mascotaRepository.findById(idMascota);
            if (mascotaEliminar.isPresent()) {

            }
            MascotaResponseDTO mascotaResponse = mapToMascotaToMascotaResponse(mascotaEliminar);
            mascotaRepository.deleteById(idMascota);
            return Optional.of(mascotaResponse);

        }
    */
}

/*
 * Notas
 * public MascotaResponseDTO registrarMascota(MascotaRequestDTO mascotaRequest)
 * {
 *
 * // Especie especieRegistrar = razaRegistrar.getEspecie(); // podemos omitir
 * esto
 * // ya que raza y especie van ligadas, pero para entender mejor el codigo esta
 * // asi
 */
