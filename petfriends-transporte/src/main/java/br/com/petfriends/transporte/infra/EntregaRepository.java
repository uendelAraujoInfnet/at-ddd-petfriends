package br.com.petfriends.transporte.infra;

import br.com.petfriends.transporte.domain.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EntregaRepository extends JpaRepository<Entrega, Long> {

    Optional<Entrega> findByPedidoId(Long pedidoId);
}