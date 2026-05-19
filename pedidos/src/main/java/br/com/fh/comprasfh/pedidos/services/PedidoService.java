package br.com.fh.comprasfh.pedidos.services;

import br.com.fh.comprasfh.pedidos.client.ServicoBancarioClient;
import br.com.fh.comprasfh.pedidos.dtos.PedidoRequestDTO;
import br.com.fh.comprasfh.pedidos.dtos.PedidoResponseDTO;
import br.com.fh.comprasfh.pedidos.enums.StatusPedido;
import br.com.fh.comprasfh.pedidos.mappers.ItemPedidoMapper;
import br.com.fh.comprasfh.pedidos.mappers.PedidoMapper;
import br.com.fh.comprasfh.pedidos.model.Pedido;
import br.com.fh.comprasfh.pedidos.repositories.ItemPedidoRepository;
import br.com.fh.comprasfh.pedidos.repositories.PedidoRepository;
import br.com.fh.comprasfh.pedidos.validator.PedidoValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ItemPedidoRepository itemPedidoRepository;
    private final PedidoValidator pedidoValidator;
    private final PedidoMapper pedidoMapper;
    private final ItemPedidoMapper itemPedidoMapper;
    private final ServicoBancarioClient servicoBancarioClient;

    @Transactional
    public PedidoResponseDTO criar(PedidoRequestDTO dto) {

        var pedido = montarPedido(dto);

        pedidoValidator.validar(pedido);

        var salvo = pedidoRepository.save(pedido);

        solicitarPagamento(salvo);

        return pedidoMapper.toResponseDTO(salvo);
    }

    private Pedido montarPedido(PedidoRequestDTO dto) {

        var pedido = pedidoMapper.map(dto);

        pedido.setStatus(StatusPedido.REALIZADO);
        pedido.setTotal(calcularTotal(dto));
        pedido.setCodigoRastreio(gerarCodigoRastreio());
        pedido.setUrlNf(gerarUrlFakeNf());

        var itens = dto.itens().stream()
                .map(itemDTO -> {
                    var item = itemPedidoMapper.map(itemDTO);
                    item.setPedido(pedido);
                    return item;
                })
                .toList();

        pedido.setItens(itens);

        return pedido;
    }

    private void solicitarPagamento(Pedido pedido) {
        var chavePagamento = servicoBancarioClient.solicitarPagamento(pedido);
        pedido.setChavePagamento(chavePagamento);
    }

    private BigDecimal calcularTotal(PedidoRequestDTO dto) {
        return dto.itens().stream()
                .map(item ->
                        item.valorUnitario().multiply(BigDecimal.valueOf(item.quantidade()))
                )
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private String gerarCodigoRastreio() {
        return "BR" + System.currentTimeMillis();
    }

    private String gerarUrlFakeNf() {
        return "https://nf.fake.com/" + UUID.randomUUID();
    }

    @Transactional(readOnly = true)
    public List<PedidoResponseDTO> listarTodos() {
        return pedidoMapper.toResponseDTOList(
                pedidoRepository.findAll()
        );
    }

    @Transactional(readOnly = true)
    public PedidoResponseDTO buscarPorCodigo(Long codigo) {

        var pedido = pedidoRepository.findById(codigo)
                .orElseThrow(() ->
                    new EntityNotFoundException("Pedido não encontrado"));

        return pedidoMapper.toResponseDTO(pedido);
    }

    @Transactional(readOnly = true)
    public List<PedidoResponseDTO> listarPorCliente(Long codigoCliente) {

        return pedidoMapper.toResponseDTOList(
            pedidoRepository.findByCodigoCliente(codigoCliente)
        );
    }

    @Transactional
    public PedidoResponseDTO atualizarStatus(Long codigo, StatusPedido novoStatus) {

        var pedido = pedidoRepository.findById(codigo)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));

        pedido.setStatus(novoStatus);
        return pedidoMapper.toResponseDTO(
                pedidoRepository.save(pedido)
        );
    }
}