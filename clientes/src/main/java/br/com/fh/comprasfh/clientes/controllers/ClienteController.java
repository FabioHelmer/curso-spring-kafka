package br.com.fh.comprasfh.clientes.controllers;


import br.com.fh.comprasfh.clientes.dtos.ClienteRequestDTO;
import br.com.fh.comprasfh.clientes.dtos.ClienteResponseDTO;
import br.com.fh.comprasfh.clientes.services.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listarTodos() {
        return ResponseEntity.ok(clienteService.listarTodos());
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<ClienteResponseDTO> buscarPorId(@PathVariable Long codigo) {
        return ResponseEntity.ok(clienteService.buscarPorId(codigo));
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> salvar(@RequestBody @Valid ClienteRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.salvar(dto));
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<ClienteResponseDTO> atualizar(@PathVariable Long codigo, @RequestBody  @Valid ClienteRequestDTO dto) {
        return ResponseEntity.ok(clienteService.atualizar(codigo, dto));
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> deletar(@PathVariable Long codigo) {
        clienteService.deletar(codigo);
        return ResponseEntity.noContent().build();
    }
}
