package com.eduardo.ribeiro.challengeifood.controllers;

import com.eduardo.ribeiro.challengeifood.domain.product.Product;
import com.eduardo.ribeiro.challengeifood.domain.product.ProductDTO;
import com.eduardo.ribeiro.challengeifood.services.ProductService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService productService) { this.service = productService; }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody ProductDTO productData){
        Product newProduct = this.service.create(productData);
        return ResponseEntity.ok().body(newProduct);
    }

    @GetMapping
    public ResponseEntity<List<Product>> list(){
        List<Product> products = this.service.list();
        return ResponseEntity.ok().body(products);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@RequestBody ProductDTO productData, @PathVariable String id) {
        Product updatedProduct = this.service.update(id, productData);
        return ResponseEntity.ok().body(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> delete(@PathVariable String id){
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
