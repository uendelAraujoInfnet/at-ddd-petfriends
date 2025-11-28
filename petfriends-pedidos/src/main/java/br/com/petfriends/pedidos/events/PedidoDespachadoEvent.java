package br.com.petfriends.pedidos.events;

import java.math.BigDecimal;

public class PedidoDespachadoEvent {

    private Long pedidoId;
    private Long clienteId;
    private EnderecoEntregaEvent enderecoEntrega;
    private BigDecimal valorFrete;

    public PedidoDespachadoEvent(Long pedidoId, Long clienteId,
                                 EnderecoEntregaEvent enderecoEntrega,
                                 BigDecimal valorFrete) {
        this.pedidoId = pedidoId;
        this.clienteId = clienteId;
        this.enderecoEntrega = enderecoEntrega;
        this.valorFrete = valorFrete;
    }

    public Long getPedidoId() { return pedidoId; }
    public Long getClienteId() { return clienteId; }
    public EnderecoEntregaEvent getEnderecoEntrega() { return enderecoEntrega; }
    public BigDecimal getValorFrete() { return valorFrete; }

    public static class EnderecoEntregaEvent {
        private String logradouro;
        private String numero;
        private String complemento;
        private String bairro;
        private String cidade;
        private String estado;
        private String cep;

        public EnderecoEntregaEvent(String logradouro, String numero, String complemento,
                                    String bairro, String cidade, String estado, String cep) {
            this.logradouro = logradouro;
            this.numero = numero;
            this.complemento = complemento;
            this.bairro = bairro;
            this.cidade = cidade;
            this.estado = estado;
            this.cep = cep;
        }

        public String getLogradouro() { return logradouro; }
        public String getNumero() { return numero; }
        public String getComplemento() { return complemento; }
        public String getBairro() { return bairro; }
        public String getCidade() { return cidade; }
        public String getEstado() { return estado; }
        public String getCep() { return cep; }
    }
}