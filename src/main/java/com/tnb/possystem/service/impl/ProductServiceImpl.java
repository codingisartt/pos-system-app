package com.tnb.possystem.service.impl;

import com.tnb.possystem.mapper.ProductMapper;
import com.tnb.possystem.modal.Category;
import com.tnb.possystem.modal.Product;
import com.tnb.possystem.modal.Store;
import com.tnb.possystem.modal.User;
import com.tnb.possystem.payload.dto.ProductDto;
import com.tnb.possystem.repository.CategoryRepository;
import com.tnb.possystem.repository.ProductRepository;
import com.tnb.possystem.repository.StoreRepository;
import com.tnb.possystem.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public ProductDto createProduct(ProductDto productDto, User user) throws Exception {
        Store store = storeRepository.findById(productDto.getStoreId())
                .orElseThrow(() -> new Exception("Store not found"));
        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new Exception("Category not found"));
        Product product = ProductMapper.toEntity(productDto, store, category);
        Product savedproduct = productRepository.save(product);
        return ProductMapper.toDto(savedproduct);
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto, User user) throws Exception {
        Product product = productRepository.findById(id).orElseThrow(
                ()-> new Exception("Product not found")
        );

        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setSku(productDto.getSku());
        product.setImage(productDto.getImage());
        product.setMrp(productDto.getMrp());
        product.setSellingPrice(productDto.getSellingPrice());
        product.setBrand(productDto.getBrand());
        product.setUpdatedAt(LocalDateTime.now());

        if (productDto.getCategoryId() != null) {
            Category category = categoryRepository.findById(productDto.getCategoryId())
                    .orElseThrow(() -> new Exception("Category not found"));
            product.setCategory(category);
        }

        Product updatedproduct = productRepository.save(product);
        return ProductMapper.toDto(updatedproduct);
    }

    @Override
    public void deleteProduct(Long id, User user) throws Exception {
        Product product = productRepository.findById(id).orElseThrow(
                ()-> new Exception("Product not Found")
        );
        productRepository.delete(product);
    }

    @Override
    public List<ProductDto> getProductsByStoreId(Long storeId) {
        List<Product> products = productRepository.findByStoreId(storeId);
        return products.stream().map(ProductMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> searchByKeyword(Long storeId, String keyword) {
        List<Product> products = productRepository.searchByKeyword(storeId, keyword);
        return products.stream().map(ProductMapper::toDto).collect(Collectors.toList());
    }
}
