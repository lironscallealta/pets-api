package cl.duoc.api_mascotas.service;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;

import cl.duoc.api_mascotas.dto.request.MascotaRequestDTO;
import cl.duoc.api_mascotas.dto.response.EspecieResponseDTO;
import cl.duoc.api_mascotas.dto.response.MascotaResponseDTO;
import cl.duoc.api_mascotas.dto.response.RazaResponseDTO;
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

        mascotaResponse.setId(mascotaModel.getId());
        mascotaResponse.setNombreMascota(mascotaModel.getNombreMascota());
        mascotaResponse.setFechaNacimientoMascota(mascotaModel.getFechaNacimientoMascota());
        mascotaResponse.setEspecieResponse(especieResponse);
        mascotaResponse.setRazaResponse(razaResponse);
        mascotaResponse.setIdCliente(1L); // Esto modificar con webclient
        // despues. Ahora es para prueba

        return mascotaResponse;

    }

    private MascotaResponseDTO registrarMascota(MascotaRequestDTO mascotaRequest){
        
        especieRepository.findById(mascotaRequest.getIdEspecie()).orElseThrow(() -> new RuntimeException("No existe especie")); // moverlos a clase excepciones
        razaRepository.findById(mascotaRequest.getIdRaza()).orElseThrow(() -> new RuntimeException("raza no existente")); // excepciones
        // Clientcleinte.findById(mascotaRequest.getIdCliente()).orElseThrow(() -> new RuntimeException("Cliente no existente")); -> esto esta para despues 


        Mascota mascotaModel = new Mascota();

        mascotaModel.setNombreMascota(mascotaRequest.getNombreMascota());
        mascotaModel.setFechaNacimientoMascota(mascotaRequest.getFechaNacimientoMascota());
        

        
        
        
        return null;
    }

}
