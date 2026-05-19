package br.com.fh.comprasfh.pedidos.repositories;

import br.com.fh.comprasfh.pedidos.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByCodigoCliente(Long codigoCliente);
}