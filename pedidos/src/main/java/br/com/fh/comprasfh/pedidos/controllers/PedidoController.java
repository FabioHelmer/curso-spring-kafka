package br.com.fh.comprasfh.pedidos.controllers;

import br.com.fh.comprasfh.pedidos.dtos.ErroReponse;
import br.com.fh.comprasfh.pedidos.dtos.PedidoRequestDTO;
import br.com.fh.comprasfh.pedidos.dtos.PedidoResponseDTO;
import br.com.fh.comprasfh.pedidos.enums.StatusPedido;
import br.com.fh.comprasfh.pedidos.exceptions.ValidatorException;
import br.com.fh.comprasfh.pedidos.services.PedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Object> criar(@RequestBody @Valid PedidoRequestDTO dto) {
        try{
            return ResponseEntity.status(201).body(pedidoService.criar(dto));
        }catch (ValidatorException e){
            var erro = new ErroReponse("Erro de Validação", e.getField(), e.getMessage());
            return ResponseEntity.badRequest().body(erro);
        }
    }

    @GetMapping
    public ResponseEntity<List<PedidoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(pedidoService.listarTodos());
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<PedidoResponseDTO> buscarPorCodigo(@PathVariable Long codigo) {
        return ResponseEntity.ok(pedidoService.buscarPorCodigo(codigo));
    }

    @GetMapping("/cliente/{codigoCliente}")
    public ResponseEntity<List<PedidoResponseDTO>> listarPorCliente(@PathVariable Long codigoCliente) {
        return ResponseEntity.ok(pedidoService.listarPorCliente(codigoCliente));
    }

    @PatchMapping("/{codigo}/status")
    public ResponseEntity<PedidoResponseDTO> atualizarStatus(
            @PathVariable Long codigo,
            @RequestParam StatusPedido status) {
        return ResponseEntity.ok(pedidoService.atualizarStatus(codigo, status));
    }
}