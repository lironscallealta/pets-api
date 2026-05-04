package cl.duoc.api_mascotas.config;

import org.springframework.web.reactive.function.client.WebClient;

public class UsuarioWebClientConfig {

    public WebClient UsuarioWebClient() {

        return WebClient.builder().baseUrl("http://localhost:8081").build();

    }

}
