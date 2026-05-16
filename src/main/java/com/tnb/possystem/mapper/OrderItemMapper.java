package com.tnb.possystem.mapper;

import com.tnb.possystem.modal.OrderItem;
import com.tnb.possystem.payload.dto.OrderItemDto;

public class OrderItemMapper {

    public static OrderItemDto toDto(OrderItem item) {
        if (item == null) return null;
        return OrderItemDto.builder()
                .id(item.getId())
                .productId(item.getProduct().getId())
                .quantity(item.getQuantity())
                .price(item.getPrice())
                .product(ProductMapper.toDto(item.getProduct()))
                .build();
    }
}
