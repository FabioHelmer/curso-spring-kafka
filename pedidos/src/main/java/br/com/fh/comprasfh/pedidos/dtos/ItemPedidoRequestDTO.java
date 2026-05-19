package br.com.fh.comprasfh.pedidos.dtos;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record ItemPedidoRequestDTO(

        @NotNull(message = "Produto é obrigatório")
        Long codigoProduto,

        @NotNull(message = "Quantidade é obrigatória")
        @Positive(message = "Quantidade deve ser maior que zero")
        Integer quantidade,

        @NotNull(message = "Valor unitário é obrigatório")
        @Positive(message = "Valor unitário deve ser maior que zero")
        BigDecimal valorUnitario

) {}