package br.com.fh.comprasfh.produtos.dtos;

import java.math.BigDecimal;

public record ProdutoRequest(String nome,
                             BigDecimal valorUnitario) {
}
