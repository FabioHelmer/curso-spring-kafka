package br.com.fh.comprasfh.pedidos.mappers;

import br.com.fh.comprasfh.pedidos.dtos.ItemPedidoRequestDTO;
import br.com.fh.comprasfh.pedidos.dtos.ItemPedidoResponseDTO;
import br.com.fh.comprasfh.pedidos.model.ItemPedido;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemPedidoMapper {

    ItemPedido map(ItemPedidoRequestDTO dto);

    ItemPedidoResponseDTO toResponseDTO(ItemPedido entity);

    List<ItemPedidoResponseDTO> toResponseDTOList(List<ItemPedido> entities);
}
