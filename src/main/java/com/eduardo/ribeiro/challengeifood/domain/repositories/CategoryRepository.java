package com.eduardo.ribeiro.challengeifood.domain.repositories;

import com.eduardo.ribeiro.challengeifood.domain.category.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {

}
