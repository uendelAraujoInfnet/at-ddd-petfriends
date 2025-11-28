package br.com.petfriends.pedidos.domain;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long clienteId;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @ElementCollection
    @CollectionTable(name = "pedidos_itens", joinColumns = @JoinColumn(name = "pedido_id"))
    private List<ItemPedido> itens = new ArrayList<>();

    protected Pedido() {}

    public Pedido(Long clienteId, List<ItemPedido> itens) {
        this.clienteId = clienteId;
        this.itens.addAll(itens);
        this.status = StatusPedido.FECHADO; // pagamento confirmado
    }

    public Long getId() { return id; }
    public Long getClienteId() { return clienteId; }
    public List<ItemPedido> getItens() { return itens; }
}