package br.com.petfriends.pedidos.api;

import br.com.petfriends.pedidos.domain.ItemPedido;
import br.com.petfriends.pedidos.domain.Pedido;
import br.com.petfriends.pedidos.events.PedidoDespachadoEvent;
import br.com.petfriends.pedidos.infra.PedidoRepository;
import br.com.petfriends.pedidos.service.PedidoService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService service;
    private final PedidoRepository repository;

    public PedidoController(PedidoService service, PedidoRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @PostMapping
    public Pedido criar(@RequestBody CriarPedidoRequest req) {
        List<ItemPedido> itens = req.itens().stream()
                .map(i -> new ItemPedido(i.produtoId(), i.quantidade()))
                .toList();
        return service.criarPedido(req.clienteId(), itens);
    }

    @PostMapping("/{id}/despachar")
    public void despachar(@PathVariable Long id, @RequestBody DespacharPedidoRequest req) {
        Pedido pedido = repository.findById(id).orElseThrow();
        var endereco = new PedidoDespachadoEvent.EnderecoEntregaEvent(
                req.endereco().logradouro(),
                req.endereco().numero(),
                req.endereco().complemento(),
                req.endereco().bairro(),
                req.endereco().cidade(),
                req.endereco().estado(),
                req.endereco().cep()
        );
        service.despacharPedido(pedido, endereco, req.valorFrete());
    }

    public record CriarPedidoRequest(Long clienteId, List<ItemReq> itens) {
        public record ItemReq(Long produtoId, Integer quantidade) {}
    }

    public record DespacharPedidoRequest(EnderecoReq endereco, BigDecimal valorFrete) {
        public record EnderecoReq(
                String logradouro, String numero, String complemento,
                String bairro, String cidade, String estado, String cep) {}
    }
}