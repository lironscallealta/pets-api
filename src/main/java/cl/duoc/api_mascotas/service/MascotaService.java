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
import cl.duoc.api_mascotas.exception.ResourceNotFoundException;
import cl.duoc.api_mascotas.model.Mascota;
import cl.duoc.api_mascotas.model.Raza;
import cl.duoc.api_mascotas.repository.MascotaRepository;
import cl.duoc.api_mascotas.repository.RazaRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
// import cl.duoc.api_mascotas.dto.response.UsuarioResponseDTO;
// import cl.duoc.api_mascotas.repository.EspecieRepository;

@Service
@RequiredArgsConstructor
public class MascotaService {

    private final MascotaRepository mascotaRepository;
    private final RazaRepository razaRepository;

    private MascotaResponseDTO mapToMascotaToMascotaResponse(Mascota mascotaModel) {

        MascotaResponseDTO mascotaResponse = new MascotaResponseDTO();
        EspecieResponseDTO especieResponse = new EspecieResponseDTO();
        RazaResponseDTO razaResponse = new RazaResponseDTO();
        // UsuarioResponseDTO usuarioResponse = new UsuarioResponseDTO(); // ->
        // Revisarlo despues aca iria webclient
        // Especie especie = new Especie();

        // Seteando Especie
        especieResponse.setId(mascotaModel.getRaza().getEspecie().getId());
        especieResponse.setNombreEspecie(mascotaModel.getRaza().getEspecie().getNombreEspecie());
        especieResponse.setDescripcionEspecie(
                mascotaModel.getRaza().getEspecie().getDescripcionEspecie());
        especieResponse.setExoticaBoolean(mascotaModel.getRaza().getEspecie().getExoticaBoolean());

        // Seteando Raza
        razaResponse.setId(mascotaModel.getRaza().getId());
        razaResponse.setNombreRaza(mascotaModel.getRaza().getNombreRaza());
        razaResponse.setDescripcionRaza(mascotaModel.getRaza().getDescripcionRaza());
        razaResponse.setEspecieResponse(especieResponse);

        // Seteando Clase Maestra
        mascotaResponse.setId(mascotaModel.getId());
        mascotaResponse.setNombreMascota(mascotaModel.getNombreMascota());
        mascotaResponse.setFechaNacimientoMascota(mascotaModel.getFechaNacimientoMascota());
        mascotaResponse.setEsDocilBoolean(mascotaModel.getEsDocilBoolean());
        mascotaResponse.setRazaResponse(razaResponse);
        mascotaResponse.setIdCliente(null);

        return mascotaResponse;
    }

    @Transactional
    public MascotaResponseDTO registrarMascota(MascotaRequestDTO mascotaRequest) {

        Raza razaRegistrar = razaRepository
                .findById(mascotaRequest.getIdRaza())
                .orElseThrow(() ->
                        new ResourceNotFoundException("La raza con ID " + mascotaRequest.getIdRaza() + " no existe"));

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

        Mascota mascotaEncontrarId = mascotaRepository
                .findById(idMascota)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró la mascota con ID: " + idMascota));
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

    public List<MascotaResponseDTO> consultarMascotasdos() {

        List<MascotaResponseDTO> mascotaResponseList = new ArrayList<>();

        List<Mascota> mascotaEncontrar = mascotaRepository.findAll();

        for (Mascota mascota : mascotaEncontrar) {
            MascotaResponseDTO mascotaResponse = mapToMascotaToMascotaResponse(mascota);
            mascotaResponseList.add(mascotaResponse);
        }
        return mascotaResponseList;
    }

    @Transactional
    public Optional<MascotaResponseDTO> actualizarMascota(Long idMascota, MascotaRequestDTO nuevosDatos) {

        Mascota mascotaModificar = mascotaRepository
                .findById(idMascota)
                .orElseThrow(() -> new ResourceNotFoundException("no se encontro la mascota con id: " + idMascota));
        mascotaModificar.setNombreMascota(nuevosDatos.getNombreMascota());
        mascotaModificar.setFechaNacimientoMascota(nuevosDatos.getFechaNacimientoMascota());
        mascotaModificar.setEsDocilBoolean(nuevosDatos.getEsDocilBoolean());
        mascotaModificar.setIdCliente(nuevosDatos.getIdCliente());
        MascotaResponseDTO mascotaResponse = mapToMascotaToMascotaResponse(mascotaModificar);
        return Optional.of(mascotaResponse);
    }

    @Transactional
    public void eliminarMascotaId(Long idMascota) {

        Mascota mascotaEliminar = mascotaRepository
                .findById(idMascota)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "no se puede eliminar porque no existe la mascota con id: " + idMascota));
        mascotaRepository.delete(mascotaEliminar);
    }
}
