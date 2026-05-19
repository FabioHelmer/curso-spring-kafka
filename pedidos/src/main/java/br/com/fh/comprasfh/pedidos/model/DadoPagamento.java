package br.com.fh.comprasfh.pedidos.model;

import br.com.fh.comprasfh.pedidos.enums.TipoPagamento;
import lombok.Data;

@Data
public class DadoPagamento {

    private String dados;
    private TipoPagamento tipoPagamento;

}
