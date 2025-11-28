package br.com.petfriends.almoxarifado.api;

import br.com.petfriends.almoxarifado.domain.ReservaEstoque;
import br.com.petfriends.almoxarifado.infra.ReservaEstoqueRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaEstoqueController {

    private final ReservaEstoqueRepository repository;

    public ReservaEstoqueController(ReservaEstoqueRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<ReservaEstoque> listar() {
        return repository.findAll();
    }

    @GetMapping("/pedido/{pedidoId}")
    public ReservaEstoque porPedido(@PathVariable Long pedidoId) {
        return repository.findByPedidoId(pedidoId)
                .orElseThrow(() -> new RuntimeException("Reserva não encontrada"));
    }
}