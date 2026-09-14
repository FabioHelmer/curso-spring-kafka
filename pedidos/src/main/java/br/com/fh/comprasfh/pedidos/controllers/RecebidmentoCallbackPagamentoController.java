package br.com.fh.comprasfh.pedidos.controllers;

import br.com.fh.comprasfh.pedidos.dtos.ErroResponseDTO;
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
    public ResponseEntity<PedidoResponseDTO> atualizarStatusPagamento(
            @RequestBody RecebimentoCallbackPagamentoDTO body,
            @RequestHeader(name = "apiKey") String apiKey) {

        PedidoResponseDTO pedidoResponseDTO = pedidoService.atualizarStatus(body);
        return ResponseEntity.ok(pedidoResponseDTO);
    }



}
