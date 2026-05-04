package cl.duoc.api_mascotas.service;

import org.springframework.stereotype.Service;

import cl.duoc.api_mascotas.client.UsuarioClient;
import cl.duoc.api_mascotas.dto.request.MascotaRequestDTO;
import cl.duoc.api_mascotas.dto.response.MascotaResponseDTO;

import cl.duoc.api_mascotas.model.Mascota;
import cl.duoc.api_mascotas.repository.MascotaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MascotaService {

    private final MascotaRepository mascotaRepository;
    private final UsuarioClient mascotaClient;

    public MascotaResponseDTO mapToDtoMascotaResponse(Mascota mascotaModel) {
        MascotaResponseDTO mascotaResponse = new MascotaResponseDTO();
       // Especie
        mascotaResponse.setId(mascotaModel.getId());
        mascotaResponse.setNombreMascota(mascotaModel.getNombreMascota());
        mascotaResponse.setFechaNacimientoMascota(mascotaModel.getFechaNacimientoMascota());
       // mascotaResponse.setEspecie(mascotaModel.getEspecie().getId());
        //mascotaResponse.setRaza(mascotaModel.getRaza());
        return mascotaResponse;

    }

    public MascotaResponseDTO guardarMascota(MascotaRequestDTO mascotaRequest) {

        Mascota mascota = new Mascota();

        mascota.setNombreMascota(mascotaRequest.getNombreMascota());

        // DtoFiestaResponse fiestaresponse =
        // FiestaClient.obtenerFiestaPorId(request.getIdFiesta());
        // Hacer algo parecido para poder conectarse al microservicio de Usuario
        return null;

    }

}

/*
 * Request → Model → BD → Response ---> idea → request → model → response →
 * service → controller
 */