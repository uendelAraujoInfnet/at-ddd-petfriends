package br.com.petfriends.transporte.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "entregas")
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // referência ao Pedido do outro microserviço
    @Column(nullable = false, unique = true)
    private Long pedidoId;

    @Embedded
    private EnderecoEntrega endereco;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusEntrega status;

    private String codigoRastreio;
    private LocalDate dataPrevistaEntrega;

    protected Entrega() {
    }

    public Entrega(Long pedidoId, EnderecoEntrega endereco) {
        this.pedidoId = Objects.requireNonNull(pedidoId);
        this.endereco = Objects.requireNonNull(endereco);
        this.status = StatusEntrega.EM_PREPARACAO;
    }

    // comportamentos
    public void iniciarTransito(String codigoRastreio, LocalDate dataPrevistaEntrega) {
        this.codigoRastreio = codigoRastreio;
        this.dataPrevistaEntrega = dataPrevistaEntrega;
        this.status = StatusEntrega.EM_TRANSITO;
    }

    public void marcarEntregue() {
        this.status = StatusEntrega.ENTREGUE;
    }

    public void marcarDevolvida() {
        this.status = StatusEntrega.DEVOLVIDA;
    }

    public Long getId() {
        return id;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public EnderecoEntrega getEndereco() {
        return endereco;
    }

    public StatusEntrega getStatus() {
        return status;
    }

    public String getCodigoRastreio() {
        return codigoRastreio;
    }

    public LocalDate getDataPrevistaEntrega() {
        return dataPrevistaEntrega;
    }
}