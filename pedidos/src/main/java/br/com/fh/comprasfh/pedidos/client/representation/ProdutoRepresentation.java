package br.com.fh.comprasfh.pedidos.client.representation;

import java.math.BigDecimal;

public record ProdutoRepresentation(long codigo,
                                    String nome,
                                    BigDecimal valorUnitario) {}
