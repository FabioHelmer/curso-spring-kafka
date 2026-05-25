package br.com.fh.comprasfh.pedidos.dtos;

public record RecebimentoCallbackPagamentoDTO(Long codigo,
                                              String chavePagamento,
                                              boolean status,
                                              String observacoes
) {
}
