package br.com.fh.comprasfh.produtos.dtos;

import java.math.BigDecimal;

public record ProdutoResponse(Long codigo,
                              String nome,
                              BigDecimal valorUnitario) {
}
