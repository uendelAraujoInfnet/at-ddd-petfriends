package br.com.petfriends.pedidos.infra;

import br.com.petfriends.pedidos.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {}