package br.ufrn.registration_service.client;

import feign.okhttp.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfiguracaoCliente {
    @Bean
    public OkHttpClient client() {
        return new OkHttpClient();
    }
}
