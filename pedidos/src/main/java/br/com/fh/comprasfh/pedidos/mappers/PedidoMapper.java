package br.com.fh.comprasfh.pedidos.mappers;

import br.com.fh.comprasfh.pedidos.dtos.PedidoRequestDTO;
import br.com.fh.comprasfh.pedidos.dtos.PedidoResponseDTO;
import br.com.fh.comprasfh.pedidos.model.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = ItemPedidoMapper.class)
public interface PedidoMapper {

    @Mapping(source = "dadosPagamentos", target = "dadosPagamentos")
    Pedido map(PedidoRequestDTO dto);

    PedidoResponseDTO toResponseDTO(Pedido entity);

    List<PedidoResponseDTO> toResponseDTOList(List<Pedido> entities);

}
