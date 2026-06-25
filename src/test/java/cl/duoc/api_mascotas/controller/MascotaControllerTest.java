/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.api_mascotas.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import cl.duoc.api_mascotas.dto.request.MascotaRequestDTO;
import cl.duoc.api_mascotas.dto.response.MascotaResponseDTO;
import cl.duoc.api_mascotas.exception.GlobalExceptionHandler;
import cl.duoc.api_mascotas.exception.ResourceNotFoundException;
import cl.duoc.api_mascotas.security.JwtAuthFilter;
import cl.duoc.api_mascotas.service.MascotaService;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.json.JsonMapper;

@WebMvcTest(MascotaController.class)
@AutoConfigureMockMvc(addFilters = false)
@Import(GlobalExceptionHandler.class)
@ActiveProfiles("test")
class MascotaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JsonMapper jsonMapper;

    @MockitoBean
    private MascotaService mascotaService;

    @MockitoBean
    private JwtAuthFilter jwtAuthFilter;

    @Test
    void registrarMascotaDebeRetornar201CuandoRequestEsValido() throws Exception {
        MascotaRequestDTO request = new MascotaRequestDTO("Firulais", LocalDate.of(2020, 5, 15), true, 1L, 1L);
        MascotaResponseDTO response = new MascotaResponseDTO(1L, "Firulais", LocalDate.of(2020, 5, 15), true, null, 1L);

        when(mascotaService.registrarMascota(any(MascotaRequestDTO.class))).thenReturn(response);

        mockMvc.perform(post("/api/v1/pets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombreMascota").value("Firulais"));

        verify(mascotaService).registrarMascota(any(MascotaRequestDTO.class));
    }

    @Test
    void registrarMascotaDebeRetornar400CuandoNombreEsInvalido() throws Exception {
        MascotaRequestDTO request = new MascotaRequestDTO("A", LocalDate.of(2020, 5, 15), true, 1L, 1L);

        mockMvc.perform(post("/api/v1/pets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());

        verify(mascotaService, never()).registrarMascota(any(MascotaRequestDTO.class));
    }

    @Test
    void consultarMascotaIdDebeRetornar200CuandoExiste() throws Exception {
        MascotaResponseDTO response = new MascotaResponseDTO(1L, "Firulais", LocalDate.of(2020, 5, 15), true, null, 1L);

        when(mascotaService.consultarMascotaId(1L)).thenReturn(Optional.of(response));

        mockMvc.perform(get("/api/v1/pets/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreMascota").value("Firulais"));
    }

    @Test
    void consultarMascotaIdDebeRetornar404CuandoNoExiste() throws Exception {
        when(mascotaService.consultarMascotaId(99L))
                .thenThrow(new ResourceNotFoundException("No se encontró la mascota con ID: 99"));

        mockMvc.perform(get("/api/v1/pets/99")).andExpect(status().isNotFound());
    }

    @Test
    void consultarMascotasDebeRetornar200() throws Exception {
        MascotaResponseDTO response = new MascotaResponseDTO(1L, "Firulais", LocalDate.of(2020, 5, 15), true, null, 1L);

        when(mascotaService.consultarMascotas()).thenReturn(List.of(response));

        mockMvc.perform(get("/api/v1/pets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombreMascota").value("Firulais"));
    }

    @Test
    void actualizarMascotaDebeRetornar202CuandoExiste() throws Exception {
        MascotaRequestDTO request =
                new MascotaRequestDTO("Firulais Actualizado", LocalDate.of(2020, 5, 15), false, 2L, 1L);
        MascotaResponseDTO response =
                new MascotaResponseDTO(1L, "Firulais Actualizado", LocalDate.of(2020, 5, 15), false, null, 1L);

        when(mascotaService.actualizarMascota(eq(1L), any(MascotaRequestDTO.class)))
                .thenReturn(Optional.of(response));

        mockMvc.perform(put("/api/v1/pets/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMapper.writeValueAsString(request)))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.nombreMascota").value("Firulais Actualizado"));
    }

    @Test
    void eliminarMascotaIdDebeRetornar204() throws Exception {
        mockMvc.perform(delete("/api/v1/pets/1")).andExpect(status().isNoContent());

        verify(mascotaService).eliminarMascotaId(eq(1L));
    }

    @Test
    void eliminarMascotaIdDebeRetornar404CuandoNoExiste() throws Exception {
        doThrow(new ResourceNotFoundException("no se puede eliminar porque no existe la mascota con id: 99"))
                .when(mascotaService)
                .eliminarMascotaId(99L);

        mockMvc.perform(delete("/api/v1/pets/99")).andExpect(status().isNotFound());
    }
}
