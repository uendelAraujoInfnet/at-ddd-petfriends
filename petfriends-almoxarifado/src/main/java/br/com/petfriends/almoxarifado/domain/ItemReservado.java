package br.com.petfriends.almoxarifado.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class ItemReservado {

    @Column(nullable = false)
    private Long produtoId;

    @Column(nullable = false)
    private Integer quantidade;

    protected ItemReservado() {
    }

    public ItemReservado(Long produtoId, Integer quantidade) {
        this.produtoId = Objects.requireNonNull(produtoId);
        if (quantidade == null || quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
        }
        this.quantidade = quantidade;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemReservado that)) return false;
        return Objects.equals(produtoId, that.produtoId) &&
                Objects.equals(quantidade, that.quantidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(produtoId, quantidade);
    }
}
