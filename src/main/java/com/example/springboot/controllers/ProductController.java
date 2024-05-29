package com.example.springboot.controllers;

import com.example.springboot.dtos.ProductRecordDTO;
import com.example.springboot.models.ProductModel;
import com.example.springboot.services.ProductService;
import com.example.springboot.util.assembler.EntityModelAssembler;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    ProductService productService;
    private final EntityModelAssembler assembler;

    public ProductController(ProductService productService, EntityModelAssembler assembler) {
        this.productService = productService;
        this.assembler = assembler;
    }

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<ProductModel>>> getAllProducts(Pageable pageable) {
        Page<ProductModel> productsPage = productService.getAllProducts(pageable);
        PagedModel<EntityModel<ProductModel>> pagedModel = assembler.toPagedModel(productsPage);
        return ResponseEntity.status(HttpStatus.OK).body(pagedModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<ProductModel>> getOneProduct(@PathVariable String id) {
        ProductModel product = productService.getProductById(id);
        return ResponseEntity.status(HttpStatus.OK).body(assembler.toModel(product));
    }

    @PostMapping
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDTO productRecordDTO) {
        var productModel = new ProductModel();

        BeanUtils.copyProperties(productRecordDTO, productModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.saveProduct(productModel));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value = "id") String id, @RequestBody @Valid ProductRecordDTO productRecordDTO) {
        var product = productService.getProductById(id);
        BeanUtils.copyProperties(productRecordDTO, product);
        return ResponseEntity.status(HttpStatus.OK).body(productService.saveProduct(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") String id) {
        productService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted");
    }
}
