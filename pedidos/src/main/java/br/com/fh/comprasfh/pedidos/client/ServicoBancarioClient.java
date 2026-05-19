package br.com.fh.comprasfh.pedidos.client;

import br.com.fh.comprasfh.pedidos.model.Pedido;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class ServicoBancarioClient {

    public String solicitarPagamento(Pedido pedido){
        log.info("solicitando pagamento para o pedido, codigo: {}.", pedido.getCodigo());
        return UUID.randomUUID().toString();
    }

}
