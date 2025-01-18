package br.ufrn.registration_service.client;

import br.ufrn.registration_service.model.dto.Evento;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(url = "http://localhost:8080", name = "event-service", configuration = ConfiguracaoCliente.class)
public interface EventoCliente {

    @RequestMapping(method = RequestMethod.GET, value = "/events/{id}", produces = "application" +
            "/json")
    Evento buscaEventoPorId(@PathVariable("id") Long id);

//    @PatchMapping("/events/{id}/add")
    @RequestMapping(method = RequestMethod.PATCH, value = "/events/{id}/add")
    Evento adicionaParticipanteEvento(@PathVariable("id") Long id);

//    @PatchMapping("/events/{id}/remove")
    @RequestMapping(method = RequestMethod.PATCH, value = "/events/{id}/remove")
    Evento removeParticipanteEvento(@PathVariable("id") Long id);
}
