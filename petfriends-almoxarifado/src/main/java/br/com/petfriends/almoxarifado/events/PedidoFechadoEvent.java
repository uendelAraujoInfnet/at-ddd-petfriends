package br.com.petfriends.almoxarifado.events;

import java.util.List;

public class PedidoFechadoEvent {

    private Long pedidoId;
    private Long clienteId;
    private List<ItemPedidoEvent> itens;

    public Long getPedidoId() {
        return pedidoId;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public List<ItemPedidoEvent> getItens() {
        return itens;
    }

    public static class ItemPedidoEvent {
        private Long produtoId;
        private Integer quantidade;

        public Long getProdutoId() {
            return produtoId;
        }

        public Integer getQuantidade() {
            return quantidade;
        }
    }
}