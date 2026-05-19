package br.com.fh.comprasfh.pedidos.dtos;

import br.com.fh.comprasfh.pedidos.model.ItemPedido;

import java.math.BigDecimal;

public record ItemPedidoResponseDTO(
        Long codigo,
        Long codigoProduto,
        Integer quantidade,
        BigDecimal valorUnitario
) {

}