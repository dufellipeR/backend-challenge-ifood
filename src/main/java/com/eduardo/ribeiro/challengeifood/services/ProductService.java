package com.eduardo.ribeiro.challengeifood.services;

import com.eduardo.ribeiro.challengeifood.domain.category.Category;
import com.eduardo.ribeiro.challengeifood.domain.category.exceptions.CategoryNotFoundException;
import com.eduardo.ribeiro.challengeifood.domain.product.Product;
import com.eduardo.ribeiro.challengeifood.domain.product.ProductDTO;
import com.eduardo.ribeiro.challengeifood.domain.product.ProductNotFoundException;
import com.eduardo.ribeiro.challengeifood.repositories.CategoryRepository;
import com.eduardo.ribeiro.challengeifood.repositories.ProductRepository;
import com.eduardo.ribeiro.challengeifood.services.aws.AwsSnsService;
import com.eduardo.ribeiro.challengeifood.services.aws.MessageDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService{

    private final AwsSnsService snsService;
    private final CategoryService categoryService;
    private final ProductRepository repository;

    public ProductService(ProductRepository repository, CategoryService categoryService, AwsSnsService snsService){
        this.repository = repository;
        this.categoryService = categoryService;
        this.snsService = snsService;
    }

    public Product create(ProductDTO productData){
        Category category = this.categoryService.show(productData.categoryId())
                .orElseThrow(CategoryNotFoundException::new);
        Product newProduct = new Product(productData);
        newProduct.setCategory(category);
        this.repository.save(newProduct);

        this.snsService.publish(new MessageDTO(newProduct.getOwnerId()));

        return newProduct;
    }

    public List<Product> list(){
        return this.repository.findAll();
    }

    public Product update(String id, ProductDTO productData){
        Product product = this.repository.findById(id).orElseThrow(ProductNotFoundException::new);

        if(productData.categoryId() != null){
             this.categoryService.show(productData.categoryId()).ifPresent(product::setCategory);
        }

        if(!productData.title().isEmpty()) product.setTitle(productData.title());
        if(!productData.description().isEmpty()) product.setDescription(productData.description());
        if(!productData.ownerId().isEmpty()) product.setOwnerId(productData.ownerId());
        if(productData.price() != null) product.setPrice(productData.price());

        this.repository.save(product);

        this.snsService.publish(new MessageDTO(product.getOwnerId()));

        return product;
    }

    public void delete(String id){
        Product product = this.repository.findById(id).orElseThrow(ProductNotFoundException::new);
        this.repository.delete(product);

    }
}
