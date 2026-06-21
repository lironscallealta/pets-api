/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.api_mascotas.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import cl.duoc.api_mascotas.dto.request.MascotaRequestDTO;
import cl.duoc.api_mascotas.dto.response.MascotaResponseDTO;
import cl.duoc.api_mascotas.exception.BadRequestException;
import cl.duoc.api_mascotas.exception.ResourceNotFoundException;
import cl.duoc.api_mascotas.model.Especie;
import cl.duoc.api_mascotas.model.Mascota;
import cl.duoc.api_mascotas.model.Raza;
import cl.duoc.api_mascotas.repository.MascotaRepository;
import cl.duoc.api_mascotas.repository.RazaRepository;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MascotaServiceTest {

    @Mock
    private MascotaRepository mascotaRepository;

    @Mock
    private RazaRepository razaRepository;

    @InjectMocks
    private MascotaService mascotaService;

    private Raza raza;
    private MascotaRequestDTO request;

    @BeforeEach
    void setUp() {
        Especie especie = new Especie(1L, "Canino", "Perro doméstico", false);
        raza = new Raza(1L, "Labrador", "Raza mediana", especie);
        request = new MascotaRequestDTO("Firulais", LocalDate.of(2020, 5, 15), true, 1L, 1L);
    }

    @Test
    void registrarMascotaDebeGuardarYRetornarResponse() {
        when(razaRepository.findById(1L)).thenReturn(Optional.of(raza));
        when(mascotaRepository.save(any(Mascota.class))).thenAnswer(invocation -> {
            Mascota mascota = invocation.getArgument(0);
            mascota.setId(10L);
            return mascota;
        });

        MascotaResponseDTO resultado = mascotaService.registrarMascota(request);

        assertThat(resultado.getNombreMascota()).isEqualTo("Firulais");
        assertThat(resultado.getRazaResponse().getNombreRaza()).isEqualTo("Labrador");
        verify(mascotaRepository).save(any(Mascota.class));
    }

    @Test
    void registrarMascotaDebeLanzarBadRequestCuandoNombreEstaVacio() {
        request.setNombreMascota("   ");

        assertThatThrownBy(() -> mascotaService.registrarMascota(request))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("nombre");

        verify(mascotaRepository, never()).save(any(Mascota.class));
    }

    @Test
    void registrarMascotaDebeLanzarNotFoundCuandoRazaNoExiste() {
        when(razaRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> mascotaService.registrarMascota(request))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("raza");

        verify(mascotaRepository, never()).save(any(Mascota.class));
    }

    @Test
    void consultarMascotaIdDebeRetornarMascotaCuandoExiste() {
        Mascota mascota = crearMascota(5L);
        when(mascotaRepository.findById(5L)).thenReturn(Optional.of(mascota));

        Optional<MascotaResponseDTO> resultado = mascotaService.consultarMascotaId(5L);

        assertThat(resultado).isPresent();
        assertThat(resultado.get().getId()).isEqualTo(5L);
        assertThat(resultado.get().getNombreMascota()).isEqualTo("Firulais");
    }

    @Test
    void consultarMascotaIdDebeLanzarNotFoundCuandoNoExiste() {
        when(mascotaRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> mascotaService.consultarMascotaId(99L)).isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void eliminarMascotaIdDebeEliminarCuandoExiste() {
        Mascota mascota = crearMascota(3L);
        when(mascotaRepository.findById(3L)).thenReturn(Optional.of(mascota));

        mascotaService.eliminarMascotaId(3L);

        verify(mascotaRepository).delete(mascota);
    }

    private Mascota crearMascota(Long id) {
        Mascota mascota = new Mascota();
        mascota.setId(id);
        mascota.setNombreMascota("Firulais");
        mascota.setFechaNacimientoMascota(LocalDate.of(2020, 5, 15));
        mascota.setEsDocilBoolean(true);
        mascota.setRaza(raza);
        mascota.setIdCliente(1L);
        return mascota;
    }
}
