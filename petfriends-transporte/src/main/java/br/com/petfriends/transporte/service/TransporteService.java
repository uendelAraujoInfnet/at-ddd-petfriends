package br.com.petfriends.transporte.service;

import br.com.petfriends.transporte.domain.EnderecoEntrega;
import br.com.petfriends.transporte.domain.Entrega;
import br.com.petfriends.transporte.events.PedidoDespachadoEvent;
import br.com.petfriends.transporte.infra.EntregaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class TransporteService {

    private final EntregaRepository entregaRepository;

    public TransporteService(EntregaRepository entregaRepository) {
        this.entregaRepository = entregaRepository;
    }

    @Transactional
    public void processarPedidoDespachado(PedidoDespachadoEvent event) {

        // evita criar duplicado se já existir entrega para esse pedido
        if (entregaRepository.findByPedidoId(event.getPedidoId()).isPresent()) {
            return;
        }

        PedidoDespachadoEvent.EnderecoEntregaEvent e = event.getEnderecoEntrega();

        EnderecoEntrega endereco = new EnderecoEntrega(
                e.getLogradouro(),
                e.getNumero(),
                e.getComplemento(),
                e.getBairro(),
                e.getCidade(),
                e.getEstado(),
                e.getCep()
        );

        Entrega entrega = new Entrega(event.getPedidoId(), endereco);

        // regra simples: previsão de 3 dias, código de rastreio fake
        entrega.iniciarTransito("TRK-" + event.getPedidoId(), LocalDate.now().plusDays(3));

        entregaRepository.save(entrega);
    }
}