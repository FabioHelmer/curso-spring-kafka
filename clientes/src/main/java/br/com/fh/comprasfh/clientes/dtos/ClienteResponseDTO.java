package br.com.fh.comprasfh.clientes.dtos;

public record ClienteResponseDTO(
        Long codigo,
        String nome,
        String cpf,
        String logradouro,
        String numero,
        String bairro,
        String email,
        String telefone
) {
}
