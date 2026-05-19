package br.com.fh.comprasfh.produtos.services;

import br.com.fh.comprasfh.produtos.dtos.ProdutoRequest;
import br.com.fh.comprasfh.produtos.dtos.ProdutoResponse;
import br.com.fh.comprasfh.produtos.exceptions.ProdutoNotFoundException;
import br.com.fh.comprasfh.produtos.model.Produto;
import br.com.fh.comprasfh.produtos.repositories.ProdutosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutosRepository repository;

    public ProdutoResponse salvar(ProdutoRequest request){
        Produto produto = Produto.builder()
                .nome(request.nome())
                .valorUnitario(request.valorUnitario())
                .build();
        repository.save(produto);

        return new ProdutoResponse(produto.getCodigo(), produto.getNome(), produto.getValorUnitario());
    }

    public ProdutoResponse obterProdutoPorCodigo(Long codigo) {

        Produto produto = repository.findById(codigo).orElseThrow(() -> new ProdutoNotFoundException(codigo));

        return new ProdutoResponse(
                produto.getCodigo(),
                produto.getNome(),
                produto.getValorUnitario()
        );
    }


}
