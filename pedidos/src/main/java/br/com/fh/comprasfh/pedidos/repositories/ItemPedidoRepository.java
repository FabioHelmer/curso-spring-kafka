package br.com.fh.comprasfh.pedidos.repositories;

import br.com.fh.comprasfh.pedidos.model.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
}