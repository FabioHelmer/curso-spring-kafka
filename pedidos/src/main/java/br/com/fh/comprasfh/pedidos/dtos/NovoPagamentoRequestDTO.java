package br.com.fh.comprasfh.pedidos.dtos;

import br.com.fh.comprasfh.pedidos.enums.TipoPagamento;

public record NovoPagamentoRequestDTO(
        String dadosCartao,
        TipoPagamento tipoPagamento
) {
}
