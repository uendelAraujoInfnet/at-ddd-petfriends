package br.com.petfriends.transporte.messaging;

import br.com.petfriends.transporte.events.PedidoDespachadoEvent;
import br.com.petfriends.transporte.service.TransporteService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class PedidoTransporteEventosConfig {

    @Bean
    public Consumer<PedidoDespachadoEvent> pedidoDespachadoConsumer(TransporteService service) {
        return service::processarPedidoDespachado;
    }
}