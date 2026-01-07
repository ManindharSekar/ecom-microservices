package com.ecom.app.service;

import com.ecom.app.dto.ProductRequest;
import com.ecom.app.dto.ProductResponse;
import com.ecom.app.model.Product;
import com.ecom.app.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;


    public ProductResponse SaveProduct(ProductRequest productRequest) {
        Product product = new Product();
        UpdateProductRequest(product,productRequest);
        Product savedProduct=productRepository.save(product);
        return mapToProductResponse(savedProduct);

    }

    private ProductResponse mapToProductResponse(Product savedProduct) {
        ProductResponse response = new ProductResponse();
        response.setId(savedProduct.getId());
        response.setName(savedProduct.getName());
        response.setDescription(savedProduct.getDescription());
        response.setPrice(savedProduct.getPrice());
        response.setStockQuantity(savedProduct.getStockQuantity());
        response.setImageUrl(savedProduct.getImageUrl());
        response.setCategory(savedProduct.getCategory());
        response.setActive(savedProduct.getActive());
        return response;
    }

    private void UpdateProductRequest(Product product, ProductRequest productRequest) {
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setStockQuantity(productRequest.getStockQuantity());
        product.setCategory(productRequest.getCategory());
        product.setImageUrl(productRequest.getImageUrl());
    }

    public Optional<ProductResponse> updateProduct(ProductRequest productRequest, Long id) {
        return productRepository.findById(id).map(existingProduct->{
            UpdateProductRequest(existingProduct,productRequest);
            Product saveProduct=productRepository.save(existingProduct);
            return mapToProductResponse(saveProduct);
        });
    }
}
