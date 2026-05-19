package br.com.fh.comprasfh.pedidos.client;

import br.com.fh.comprasfh.pedidos.client.representation.ProdutoRepresentation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "produtos", url = "${fhcompras.pedidos.clients.produtos.url}")
public interface ProdutosClient {

    @GetMapping("{codigo}")
    ResponseEntity<ProdutoRepresentation> obterProdutoPorCodigo(@PathVariable Long codigo);

}
