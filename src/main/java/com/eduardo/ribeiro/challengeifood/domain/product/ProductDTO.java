package com.eduardo.ribeiro.challengeifood.domain.product;

import com.eduardo.ribeiro.challengeifood.domain.category.Category;

public record ProductDTO(String title, String description, Integer price, String categoryId, String ownerId) {}
