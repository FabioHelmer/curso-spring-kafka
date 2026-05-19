package br.com.fh.comprasfh.pedidos.dtos;


import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;

public record PedidoRequestDTO(

        @NotNull(message = "Cliente é obrigatório")
        Long codigoCliente,

        @NotNull(message = "Total é obrigatório")
        @Positive(message = "Total deve ser maior que zero")
        BigDecimal total,

        String observacoes,

        @NotEmpty(message = "O pedido deve ter ao menos um item")
        @Valid
        List<ItemPedidoRequestDTO> itens,

        DadosPagamentosDTO dadosPagamentos

) {}
