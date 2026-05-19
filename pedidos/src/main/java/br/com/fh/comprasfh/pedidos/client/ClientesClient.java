package br.com.fh.comprasfh.pedidos.client;

import br.com.fh.comprasfh.pedidos.client.representation.ClienteRepresentation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "clientes", url = "${fhcompras.pedidos.clients.clientes.url}")
public interface ClientesClient {

    @GetMapping("/{codigo}")
    ResponseEntity<ClienteRepresentation> buscarPorId(@PathVariable Long codigo);



}
