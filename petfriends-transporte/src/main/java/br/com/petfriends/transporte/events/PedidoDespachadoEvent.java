package br.com.petfriends.transporte.events;

import java.math.BigDecimal;

public class PedidoDespachadoEvent {

    private Long pedidoId;
    private Long clienteId;
    private EnderecoEntregaEvent enderecoEntrega;
    private BigDecimal valorFrete;

    public Long getPedidoId() {
        return pedidoId;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public EnderecoEntregaEvent getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public BigDecimal getValorFrete() {
        return valorFrete;
    }

    public static class EnderecoEntregaEvent {
        private String logradouro;
        private String numero;
        private String complemento;
        private String bairro;
        private String cidade;
        private String estado;
        private String cep;

        public String getLogradouro() { return logradouro; }
        public String getNumero() { return numero; }
        public String getComplemento() { return complemento; }
        public String getBairro() { return bairro; }
        public String getCidade() { return cidade; }
        public String getEstado() { return estado; }
        public String getCep() { return cep; }
    }
}