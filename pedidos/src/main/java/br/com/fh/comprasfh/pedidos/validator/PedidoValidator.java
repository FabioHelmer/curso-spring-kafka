package br.com.fh.comprasfh.pedidos.validator;

import br.com.fh.comprasfh.pedidos.client.ClientesClient;
import br.com.fh.comprasfh.pedidos.client.ProdutosClient;
import br.com.fh.comprasfh.pedidos.client.representation.ClienteRepresentation;
import br.com.fh.comprasfh.pedidos.client.representation.ProdutoRepresentation;
import br.com.fh.comprasfh.pedidos.exceptions.ValidatorException;
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

        Long codigoCliente = pedido.getCodigoCliente();
        try {
            ResponseEntity<ClienteRepresentation> response = clientesClient.buscarPorId(codigoCliente);
            if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
                throw new EntityNotFoundException("Cliente não encontrado: " + codigoCliente);
            }
            log.info("Cliente encontrado: {}", codigoCliente);
        } catch (FeignException.NotFound notFound) {
            throw new ValidatorException("codigoCliente", "Cliente de codigo " + codigoCliente + " não encontrado.");
        }
    }

    private void validarProdutos(Pedido pedido) {
        try {
            pedido.getItens().forEach(itemPedido -> {
                log.info("obtendo informações de produto:{}", itemPedido.getCodigoProduto());
                ResponseEntity<ProdutoRepresentation> response = produtosClient.obterProdutoPorCodigo(itemPedido.getCodigoProduto());
                if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
                    throw new ValidatorException("codigoProduto", "Produto não encontrado: " + itemPedido.getCodigoProduto());
                }
            });

        } catch (FeignException.NotFound notFound) {
            throw new ValidatorException("codigoProduto", "Produto não encontrado.");
        }

    }

}
