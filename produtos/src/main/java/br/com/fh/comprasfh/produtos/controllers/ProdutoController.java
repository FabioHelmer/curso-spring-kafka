package br.com.fh.comprasfh.produtos.controllers;

import br.com.fh.comprasfh.produtos.dtos.ProdutoRequest;
import br.com.fh.comprasfh.produtos.dtos.ProdutoResponse;
import br.com.fh.comprasfh.produtos.exceptions.ProdutoNotFoundException;
import br.com.fh.comprasfh.produtos.services.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<ProdutoResponse> salvar(@RequestBody ProdutoRequest request) {
        ProdutoResponse response = produtoService.salvar(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<ProdutoResponse> obterProdutoPorCodigo( @PathVariable Long codigo) {
        try {
            ProdutoResponse response = produtoService.obterProdutoPorCodigo(codigo);
            return ResponseEntity.ok(response);

        } catch (ProdutoNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
