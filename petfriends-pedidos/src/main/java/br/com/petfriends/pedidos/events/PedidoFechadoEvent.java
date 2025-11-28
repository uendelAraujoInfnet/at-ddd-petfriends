package br.com.petfriends.pedidos.events;

import java.util.List;

public class PedidoFechadoEvent {

    private Long pedidoId;
    private Long clienteId;
    private List<ItemPedidoEvent> itens;

    public PedidoFechadoEvent(Long pedidoId, Long clienteId, List<ItemPedidoEvent> itens) {
        this.pedidoId = pedidoId;
        this.clienteId = clienteId;
        this.itens = itens;
    }

    public Long getPedidoId() { return pedidoId; }
    public Long getClienteId() { return clienteId; }
    public List<ItemPedidoEvent> getItens() { return itens; }

    public static class ItemPedidoEvent {
        private Long produtoId;
        private Integer quantidade;

        public ItemPedidoEvent(Long produtoId, Integer quantidade) {
            this.produtoId = produtoId;
            this.quantidade = quantidade;
        }

        public Long getProdutoId() { return produtoId; }
        public Integer getQuantidade() { return quantidade; }
    }
}
