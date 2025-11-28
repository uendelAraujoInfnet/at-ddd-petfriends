package br.com.petfriends.pedidos.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public class ItemPedido {

    private Long produtoId;
    private Integer quantidade;

    protected ItemPedido() {}

    public ItemPedido(Long produtoId, Integer quantidade) {
        this.produtoId = produtoId;
        this.quantidade = quantidade;
    }

    public Long getProdutoId() { return produtoId; }
    public Integer getQuantidade() { return quantidade; }
}