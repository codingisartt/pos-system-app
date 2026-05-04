package com.tnb.possystem.service;

import com.tnb.possystem.modal.User;
import com.tnb.possystem.payload.dto.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto createProduct(ProductDto productDto, User user) throws Exception;
    ProductDto updateProduct(Long id, ProductDto productDto, User user) throws Exception;
    void deleteProduct(Long id, User user) throws Exception;
    List<ProductDto> getProductsByStoreId(Long storeId);
    List<ProductDto> searchByKeyword(Long storeId, String keyword);
}
