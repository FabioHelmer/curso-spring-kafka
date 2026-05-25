package br.com.fh.comprasfh.pedidos.controllers;

import br.com.fh.comprasfh.pedidos.dtos.PedidoResponseDTO;
import br.com.fh.comprasfh.pedidos.dtos.RecebimentoCallbackPagamentoDTO;
import br.com.fh.comprasfh.pedidos.services.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos/callback-pagamentos")
@RequiredArgsConstructor
public class RecebidmentoCallbackPagamentoController {

    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Object> atualizarStatusPagamento(@RequestBody RecebimentoCallbackPagamentoDTO body,
                                                           @RequestHeader(required = true, name = "apiKey") String apiKey){

        try{
            PedidoResponseDTO pedidoResponseDTO = pedidoService.atualizarStatus(body);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest();
        }
    }



}
