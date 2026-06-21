/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.api_mascotas.dataloader;

import cl.duoc.api_mascotas.model.Mascota;
import cl.duoc.api_mascotas.model.Raza;
import cl.duoc.api_mascotas.repository.MascotaRepository;
import cl.duoc.api_mascotas.repository.RazaRepository;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private static final int MASCOTAS_FLYWAY = 3;
    private static final int MASCOTAS_FAKE = 20;

    private final MascotaRepository mascotaRepository;
    private final RazaRepository razaRepository;

    @Override
    public void run(String... args) {
        if (mascotaRepository.count() > MASCOTAS_FLYWAY) {
            return;
        }

        List<Raza> razas = razaRepository.findAll();
        if (razas.isEmpty()) {
            return;
        }

        Faker faker = new Faker();

        for (int i = 0; i < MASCOTAS_FAKE; i++) {
            Mascota mascota = new Mascota();
            mascota.setNombreMascota(faker.animal().name());
            mascota.setFechaNacimientoMascota(faker.timeAndDate().birthday());
            mascota.setEsDocilBoolean(faker.bool().bool());
            mascota.setRaza(razas.get(ThreadLocalRandom.current().nextInt(razas.size())));
            mascota.setIdCliente((long) ThreadLocalRandom.current().nextInt(1, 6));
            mascotaRepository.save(mascota);
        }
    }
}
