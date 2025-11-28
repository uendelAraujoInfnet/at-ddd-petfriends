package br.com.petfriends.almoxarifado.infra;

import br.com.petfriends.almoxarifado.domain.ReservaEstoque;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservaEstoqueRepository extends JpaRepository<ReservaEstoque, Long> {

    Optional<ReservaEstoque> findByPedidoId(Long pedidoId);
}