package cl.duoc.api_mascotas.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cl.duoc.api_mascotas.dto.request.MascotaRequestDTO;
import cl.duoc.api_mascotas.dto.response.MascotaResponseDTO;
import cl.duoc.api_mascotas.service.MascotaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/mascotas")
@RequiredArgsConstructor
public class MascotaController {

    // comentarios segun que metodos espera escritos en readme
    // Action | Method | Endpoint

    private final MascotaService mascotaService;

    @PostMapping() // | Create | POST | `/api/v1/users`
    public ResponseEntity<MascotaResponseDTO> registrarMascota(@Valid @RequestBody MascotaRequestDTO mascotaRequest) {
        MascotaResponseDTO registrarMascota = mascotaService.registrarMascota(mascotaRequest);
        return ResponseEntity.ok(registrarMascota);

    }

    @GetMapping("/{id}") // | Get one | GET | `/api/v1/users/{id}` |
    public ResponseEntity<MascotaResponseDTO> consultarMascotaId(@PathVariable Long id) {
        Optional<MascotaResponseDTO> consultarMascotaId = mascotaService.consultarMascotaId(id);
        return ResponseEntity.ok(consultarMascotaId.get());
    }

    @GetMapping() // | Get all | GET | `/api/v1/users` |
    public List<MascotaResponseDTO> consultarMascotas() {

        List<MascotaResponseDTO> consultarMascotas = mascotaService.consultarMascotas();
        return consultarMascotas;
    }

    // metodo prueba en caso de usarlo seria por el get all anterior
    @GetMapping("/getdos") // | Get all | GET | `/api/v1/users/getdos` |
    public List<MascotaResponseDTO> consultarMascotasdos() {

        List<MascotaResponseDTO> consultarMascotas = mascotaService.consultarMascotasdos();
        return consultarMascotas;
    }
/* 
    // dejar despues misma escritura de los anteriores
    @DeleteMapping("/{id}") // | Delete | DELETE | `/api/v1/users/{id}` |
    public Optional<MascotaResponseDTO> eliminarMascotaId(Long id) {
        return mascotaService.eliminarMascotaId(id);

    }*/
}