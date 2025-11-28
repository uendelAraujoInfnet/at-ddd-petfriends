package br.com.petfriends.almoxarifado.domain;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "reservas_estoque")
public class ReservaEstoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // referência ao agregado Pedido no microserviço de Pedidos
    @Column(nullable = false, unique = true)
    private Long pedidoId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusReserva status;

    @ElementCollection
    @CollectionTable(
            name = "reservas_estoque_itens",
            joinColumns = @JoinColumn(name = "reserva_id")
    )
    private List<ItemReservado> itens = new ArrayList<>();

    protected ReservaEstoque() {}

    public ReservaEstoque(Long pedidoId, List<ItemReservado> itens) {
        this.pedidoId = Objects.requireNonNull(pedidoId);
        this.status = StatusReserva.CRIADA;
        this.itens.addAll(Objects.requireNonNull(itens));
    }

    // --- comportamentos de domínio ---
    public void reservar() {
        if (!StatusReserva.CRIADA.equals(this.status)) {
            throw new IllegalStateException("Reserva não está em estado CRIADA.");
        }
        this.status = StatusReserva.RESERVADA;
    }

    public void separar() {
        if (!StatusReserva.RESERVADA.equals(this.status)) {
            throw new IllegalStateException("Reserva não está em estado RESERVADA.");
        }
        this.status = StatusReserva.SEPARADA;
    }

    public void cancelar() {
        if (StatusReserva.CANCELADA.equals(this.status)) {
            return;
        }
        this.status = StatusReserva.CANCELADA;
    }

    public Long getId() {
        return id;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public StatusReserva getStatus() {
        return status;
    }

    public List<ItemReservado> getItens() {
        return itens;
    }
}