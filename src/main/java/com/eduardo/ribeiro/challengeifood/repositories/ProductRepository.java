package com.eduardo.ribeiro.challengeifood.repositories;

import com.eduardo.ribeiro.challengeifood.domain.product.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}
