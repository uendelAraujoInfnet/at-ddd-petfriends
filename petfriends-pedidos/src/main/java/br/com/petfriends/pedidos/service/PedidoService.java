package br.com.petfriends.pedidos.service;

import br.com.petfriends.pedidos.domain.ItemPedido;
import br.com.petfriends.pedidos.domain.Pedido;
import br.com.petfriends.pedidos.events.PedidoDespachadoEvent;
import br.com.petfriends.pedidos.events.PedidoFechadoEvent;
import br.com.petfriends.pedidos.infra.PedidoRepository;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository repository;
    private final StreamBridge streamBridge;

    public PedidoService(PedidoRepository repository, StreamBridge streamBridge) {
        this.repository = repository;
        this.streamBridge = streamBridge;
    }

    @Transactional
    public Pedido criarPedido(Long clienteId, List<ItemPedido> itens) {
        Pedido pedido = new Pedido(clienteId, itens);
        Pedido salvo = repository.save(pedido);

        // monta e envia evento de PedidoFechado
        PedidoFechadoEvent event = new PedidoFechadoEvent(
                salvo.getId(),
                salvo.getClienteId(),
                salvo.getItens().stream()
                        .map(i -> new PedidoFechadoEvent.ItemPedidoEvent(i.getProdutoId(), i.getQuantidade()))
                        .toList()
        );

        streamBridge.send("pedidoFechadoSupplier-out-0", event);
        return salvo;
    }

    @Transactional
    public void despacharPedido(Pedido pedido,
                                PedidoDespachadoEvent.EnderecoEntregaEvent endereco,
                                BigDecimal frete) {

        PedidoDespachadoEvent event = new PedidoDespachadoEvent(
                pedido.getId(),
                pedido.getClienteId(),
                endereco,
                frete
        );

        streamBridge.send("pedidoDespachadoSupplier-out-0", event);
    }
}