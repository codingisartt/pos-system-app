package com.tnb.possystem.mapper;

import com.tnb.possystem.modal.Product;
import com.tnb.possystem.modal.Store;
import com.tnb.possystem.payload.dto.ProductDto;
import com.tnb.possystem.payload.dto.StoreDto;

public class ProductMapper {

    public static ProductDto toDto(Product product)  {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .sku(product.getSku())
                .description(product.getDescription())
                .mrp(product.getMrp())
                .sellingPrice(product.getSellingPrice())
                .brand(product.getBrand())
                .storeId(product.getStore()!=null?product.getStore().getId():null)
                .image(product.getImage())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();


    }

    public static Product toEntity(ProductDto productDto, Store  store) {
        return Product.builder()
                .name(productDto.getName())
                .sku(productDto.getSku())
                .description(productDto.getDescription())
                .mrp(productDto.getMrp())
                .sellingPrice(productDto.getSellingPrice())
                .brand(productDto.getBrand())
                .build();
    }
}
