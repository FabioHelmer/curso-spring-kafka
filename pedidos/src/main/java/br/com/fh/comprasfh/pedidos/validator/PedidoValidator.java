package br.com.fh.comprasfh.pedidos.validator;

import br.com.fh.comprasfh.pedidos.client.ClientesClient;
import br.com.fh.comprasfh.pedidos.client.ProdutosClient;
import br.com.fh.comprasfh.pedidos.client.representation.ClienteRepresentation;
import br.com.fh.comprasfh.pedidos.client.representation.ProdutoRepresentation;
import br.com.fh.comprasfh.pedidos.model.Pedido;
import feign.FeignException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class PedidoValidator {

    private final ProdutosClient produtosClient;
    private final ClientesClient clientesClient;

    public void validar(Pedido pedido) {
        validarProdutos(pedido);
        validarCliente(pedido);
    }

    private void validarCliente(Pedido pedido) {

        try {
            ResponseEntity<ClienteRepresentation> response = clientesClient.buscarPorId(pedido.getCodigoCliente());
            if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
                throw new EntityNotFoundException("Cliente não encontrado: " + pedido.getCodigoCliente());
            }
            log.info("Cliente encontrado: {}", pedido.getCodigoCliente());
        } catch (FeignException.NotFound notFound) {
            log.error("Cliente não encontrado: {}", pedido.getCodigoCliente());
        }


    }

    private void validarProdutos(Pedido pedido) {
        try {
            pedido.getItens().forEach(itemPedido -> {
                log.info("obtendo informações de produto:{}", itemPedido.getCodigoProduto());
                ResponseEntity<ProdutoRepresentation> response = produtosClient.obterProdutoPorCodigo(itemPedido.getCodigoProduto());
                if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
                    throw new EntityNotFoundException("Produto não encontrado: " + itemPedido.getCodigoProduto());
                }
            });

        } catch (FeignException.NotFound notFound) {
            log.error("Produto não encontrado");
        }

    }

}
