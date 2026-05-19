package br.com.fh.comprasfh.pedidos.validator;

import br.com.fh.comprasfh.pedidos.client.ProdutosClient;
import br.com.fh.comprasfh.pedidos.client.representation.ProdutoRepresentation;
import br.com.fh.comprasfh.pedidos.model.Pedido;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PedidoValidator {

    private final ProdutosClient produtosClient;

    public void validar(Pedido pedido){
        List<Long> codigoProdutos = pedido.getItens().stream()
                .map(item -> item.getCodigo())
                .toList();

        codigoProdutos.forEach(codigo -> {
            ResponseEntity<ProdutoRepresentation> response = produtosClient.obterProdutoPorCodigo(codigo);
            if (!response.getStatusCode().is2xxSuccessful()
                    || response.getBody() == null) {

                throw new RuntimeException(
                        "Produto não encontrado: " + codigo
                );
            }
        });
    }

}
