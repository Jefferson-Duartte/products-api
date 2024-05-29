package com.example.springboot.util.assembler;

import com.example.springboot.controllers.ProductController;
import com.example.springboot.models.ProductModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class EntityModelAssembler implements RepresentationModelAssembler<ProductModel, EntityModel<ProductModel>> {

    @Override
    public EntityModel<ProductModel> toModel(ProductModel product) {

        String id = product.getIdProduct().toString();

        return EntityModel.of(product,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductController.class).getOneProduct(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductController.class).getAllProducts(PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "name")))).withRel("Product List"),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductController.class).updateProduct(id, null)).withRel("Update product"),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductController.class).deleteProduct(id)).withRel("Delete product"));

    }

    public PagedModel<EntityModel<ProductModel>> toPagedModel(Page<ProductModel> page) {
        return PagedModel.of(
                page.getContent().stream().map(this::toModel).collect(Collectors.toList()),
                new PagedModel.PageMetadata(
                        page.getSize(), page.getNumber(), page.getTotalElements(), page.getTotalPages()
                )
        );
    }
}


