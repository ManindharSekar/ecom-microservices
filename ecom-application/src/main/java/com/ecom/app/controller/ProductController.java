package com.ecom.app.controller;


import com.ecom.app.dto.ProductRequest;
import com.ecom.app.dto.ProductResponse;
import com.ecom.app.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> CreateProduct(@RequestBody ProductRequest productRequest){
        return new ResponseEntity<ProductResponse>(productService.SaveProduct(productRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> CreateProduct(@RequestBody ProductRequest productRequest, @PathVariable Long id){
        return productService.updateProduct(productRequest,id).map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }
}
