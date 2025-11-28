package br.com.petfriends.almoxarifado.service;

import br.com.petfriends.almoxarifado.domain.ItemReservado;
import br.com.petfriends.almoxarifado.domain.ReservaEstoque;
import br.com.petfriends.almoxarifado.infra.ReservaEstoqueRepository;
import br.com.petfriends.almoxarifado.events.PedidoFechadoEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AlmoxarifadoService {

    private final ReservaEstoqueRepository reservaRepository;

    public AlmoxarifadoService(ReservaEstoqueRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    @Transactional
    public void processarPedidoFechado(PedidoFechadoEvent event) {

        // converte itens do evento em VOs
        List<ItemReservado> itens = event.getItens().stream()
                .map(i -> new ItemReservado(i.getProdutoId(), i.getQuantidade()))
                .toList();

        // cria reserva
        ReservaEstoque reserva = new ReservaEstoque(event.getPedidoId(), itens);
        reserva.reservar(); // muda status de CRIADA -> RESERVADA

        reservaRepository.save(reserva);
    }
}