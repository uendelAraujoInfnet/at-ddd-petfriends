package br.com.petfriends.transporte.api;

import br.com.petfriends.transporte.domain.Entrega;
import br.com.petfriends.transporte.infra.EntregaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entregas")
public class EntregaController {

    private final EntregaRepository repository;

    public EntregaController(EntregaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Entrega> listar() {
        return repository.findAll();
    }

    @GetMapping("/pedido/{pedidoId}")
    public Entrega porPedido(@PathVariable Long pedidoId) {
        return repository.findByPedidoId(pedidoId)
                .orElseThrow(() -> new RuntimeException("Entrega não encontrada"));
    }
}