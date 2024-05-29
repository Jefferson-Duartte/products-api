package com.example.springboot.services;

import com.example.springboot.exceptions.ProductNotFoundException;
import com.example.springboot.models.ProductModel;
import com.example.springboot.repositories.ProductRepository;
import com.example.springboot.util.validator.UUIDValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<ProductModel> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public ProductModel saveProduct(ProductModel productModel) {
        return productRepository.save(productModel);

    }

    public ProductModel getProductById(String id) {
        UUIDValidator.validateUUID(id);
        UUID productUUID = UUID.fromString(id);
        Optional<ProductModel> product = productRepository.findById(productUUID);

        if (product.isEmpty()) {
            throw new ProductNotFoundException();
        }

        return product.get();
    }

    public void deleteById(String id) {
        UUIDValidator.validateUUID(id);
        UUID productUUID = UUID.fromString(id);
        Optional<ProductModel> product = productRepository.findById(productUUID);
        if (product.isEmpty()) {
            throw new ProductNotFoundException();
        }
        productRepository.deleteById(productUUID);
    }

}
