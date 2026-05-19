package br.com.fh.comprasfh.pedidos.dtos;

import br.com.fh.comprasfh.pedidos.enums.StatusPedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record PedidoResponseDTO(
        Long codigo,
        Long codigoCliente,
        LocalDateTime dataPedido,
        String chavePagamento,
        String observacoes,
        StatusPedido status,
        BigDecimal total,
        String codigoRastreio,
        String urlNf,
        List<ItemPedidoResponseDTO> itens
) {
}