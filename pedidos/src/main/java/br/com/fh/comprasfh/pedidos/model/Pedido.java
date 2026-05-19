package br.com.fh.comprasfh.pedidos.model;

import br.com.fh.comprasfh.pedidos.dtos.DadosPagamentosDTO;
import br.com.fh.comprasfh.pedidos.enums.StatusPedido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pedido")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private Long codigo;

    @Column(name = "codigo_cliente", nullable = false)
    private Long codigoCliente;

    @Column(name = "data_pedido", nullable = false)
    private LocalDateTime dataPedido;

    @Column(name = "chave_pagamento", columnDefinition = "text")
    private String chavePagamento;

    @Column(name = "observacoes", columnDefinition = "text")
    private String observacoes;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20)
    private StatusPedido status;

    @Column(name = "total", nullable = false, precision = 16, scale = 2)
    private BigDecimal total;

    @Column(name = "codigo_rastreio", length = 255)
    private String codigoRastreio;

    @Column(name = "url_nf", columnDefinition = "text")
    private String urlNf;

    @Transient
    private DadoPagamento dadosPagamentos;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itens;

    @PrePersist
    public void prePersist() {
        if (dataPedido == null) {
            dataPedido = LocalDateTime.now();
        }
    }
}