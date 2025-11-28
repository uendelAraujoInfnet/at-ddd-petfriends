package br.com.petfriends.transporte.domain;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class EnderecoEntrega {

    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;

    protected EnderecoEntrega() {
    }

    public EnderecoEntrega(String logradouro,
                           String numero,
                           String complemento,
                           String bairro,
                           String cidade,
                           String estado,
                           String cep) {
        this.logradouro = Objects.requireNonNull(logradouro);
        this.numero = Objects.requireNonNull(numero);
        this.complemento = complemento;
        this.bairro = Objects.requireNonNull(bairro);
        this.cidade = Objects.requireNonNull(cidade);
        this.estado = Objects.requireNonNull(estado);
        this.cep = Objects.requireNonNull(cep);
    }

    public String getLogradouro() { return logradouro; }
    public String getNumero() { return numero; }
    public String getComplemento() { return complemento; }
    public String getBairro() { return bairro; }
    public String getCidade() { return cidade; }
    public String getEstado() { return estado; }
    public String getCep() { return cep; }
}