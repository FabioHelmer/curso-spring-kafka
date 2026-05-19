package br.com.fh.comprasfh.clientes.services;


import br.com.fh.comprasfh.clientes.dtos.ClienteRequestDTO;
import br.com.fh.comprasfh.clientes.dtos.ClienteResponseDTO;
import br.com.fh.comprasfh.clientes.model.Cliente;
import br.com.fh.comprasfh.clientes.repositories.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;

    public List<ClienteResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public ClienteResponseDTO buscarPorId(Long codigo) {
        Cliente cliente = repository.findById(codigo)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));

        return toResponseDTO(cliente);
    }

    public ClienteResponseDTO salvar(ClienteRequestDTO dto) {
        Cliente cliente = toEntity(dto);
        return toResponseDTO(repository.save(cliente));
    }

    public ClienteResponseDTO atualizar(Long codigo, ClienteRequestDTO dto) {
        Cliente cliente = repository.findById(codigo)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        cliente.setNome(dto.nome());
        cliente.setCpf(dto.cpf());
        cliente.setLogradouro(dto.logradouro());
        cliente.setNumero(dto.numero());
        cliente.setBairro(dto.bairro());
        cliente.setEmail(dto.email());
        cliente.setTelefone(dto.telefone());

        return toResponseDTO(repository.save(cliente));
    }

    public void deletar(Long codigo) {
        repository.deleteById(codigo);
    }

    private Cliente toEntity(ClienteRequestDTO dto) {
        return Cliente.builder()
                .nome(dto.nome())
                .cpf(dto.cpf())
                .logradouro(dto.logradouro())
                .numero(dto.numero())
                .bairro(dto.bairro())
                .email(dto.email())
                .telefone(dto.telefone())
                .build();
    }

    private ClienteResponseDTO toResponseDTO(Cliente cliente) {
        return new ClienteResponseDTO(
                cliente.getCodigo(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getLogradouro(),
                cliente.getNumero(),
                cliente.getBairro(),
                cliente.getEmail(),
                cliente.getTelefone()
        );
    }
}