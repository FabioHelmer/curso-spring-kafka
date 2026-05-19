package br.com.fh.comprasfh.produtos.exceptions;

public class ProdutoNotFoundException extends RuntimeException {

    public ProdutoNotFoundException() {
        super("Produto não encontrado.");
    }

    public ProdutoNotFoundException(Long codigo) {
        super("Produto não encontrado para o código: " + codigo);
    }
}
