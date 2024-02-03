package com.eduardo.ribeiro.challengeifood.services;

import com.eduardo.ribeiro.challengeifood.domain.category.Category;
import com.eduardo.ribeiro.challengeifood.domain.category.exceptions.CategoryNotFoundException;
import com.eduardo.ribeiro.challengeifood.domain.product.Product;
import com.eduardo.ribeiro.challengeifood.domain.product.ProductDTO;
import com.eduardo.ribeiro.challengeifood.domain.product.ProductNotFoundException;
import com.eduardo.ribeiro.challengeifood.repositories.CategoryRepository;
import com.eduardo.ribeiro.challengeifood.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService{

    private CategoryService categoryService;
    private ProductRepository repository;

    public ProductService(ProductRepository repository, CategoryService categoryService){
        this.repository = repository;
        this.categoryService = categoryService;
    }

    public Product create(ProductDTO productData){
        Category category = this.categoryService.show(productData.categoryId())
                .orElseThrow(CategoryNotFoundException::new);
        Product newProduct = new Product(productData);
        newProduct.setCategory(category);
        this.repository.save(newProduct);
        return newProduct;
    }

    public List<Product> list(){
        return this.repository.findAll();
    }

    public Product update(String id, ProductDTO productData){
        Product product = this.repository.findById(id).orElseThrow(ProductNotFoundException::new);

        this.categoryService.show(productData.categoryId()).ifPresent(product::setCategory);

        if(!productData.title().isEmpty()) product.setTitle(productData.title());
        if(!productData.description().isEmpty()) product.setDescription(productData.description());
        if(productData.price() != null) product.setDescription(productData.description());

        return product;
    }

    public void delete(String id){
        Product product = this.repository.findById(id).orElseThrow(ProductNotFoundException::new);
        this.repository.delete(product);

    }
}
