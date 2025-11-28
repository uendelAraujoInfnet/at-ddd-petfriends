package br.com.petfriends.almoxarifado.messaging;

import br.com.petfriends.almoxarifado.events.PedidoFechadoEvent;
import br.com.petfriends.almoxarifado.service.AlmoxarifadoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class PedidoEventosConfig {

    @Bean
    public Consumer<PedidoFechadoEvent> pedidoFechadoConsumer(AlmoxarifadoService service) {
        return service::processarPedidoFechado;
    }
}