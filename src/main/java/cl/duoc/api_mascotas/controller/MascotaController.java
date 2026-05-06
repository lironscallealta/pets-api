package cl.duoc.api_mascotas.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
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

    private final MascotaService mascotaService;

    @PostMapping()
    public ResponseEntity<MascotaResponseDTO> registrarMascota(@Valid @RequestBody MascotaRequestDTO mascotaRequest) {
        MascotaResponseDTO registrarMascota = mascotaService.registrarMascota(mascotaRequest);
        return ResponseEntity.ok(registrarMascota);

    }

    @GetMapping("/{id}")
    public ResponseEntity<MascotaResponseDTO> consultarMascotaId(@PathVariable Long id) {
        Optional<MascotaResponseDTO> consultarMascota = mascotaService.consultarMascotaId(id);
        return ResponseEntity.ok(consultarMascota.get());
    }

}
