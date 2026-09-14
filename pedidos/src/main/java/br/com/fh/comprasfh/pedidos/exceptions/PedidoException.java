package br.com.fh.comprasfh.pedidos.exceptions;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PedidoException extends RuntimeException {

    private final String mensagem;
    private final String detalhe;
    private final LocalDateTime dataHora;

    public PedidoException(String mensagem, String detalhe) {
        super(mensagem);

        this.mensagem = mensagem;
        this.detalhe = detalhe;
        this.dataHora = LocalDateTime.now();
    }
}
