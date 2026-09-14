package br.com.fh.comprasfh.pedidos.dtos;

import java.time.LocalDateTime;

public record ErroResponseDTO(
        String mensagem,
        String detalhe,
        LocalDateTime dataHora
) {

    public ErroResponseDTO(String mensagem, String detalhe) {
        this(mensagem, detalhe, LocalDateTime.now());
    }
}
