package br.com.fh.comprasfh.pedidos.dtos;

import br.com.fh.comprasfh.pedidos.enums.TipoPagamento;

public record DadosPagamentosDTO(
        String dados, TipoPagamento tipoPagamento
) {
}
