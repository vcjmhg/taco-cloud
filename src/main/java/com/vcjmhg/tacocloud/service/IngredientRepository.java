package com.vcjmhg.tacocloud.service;

import com.vcjmhg.tacocloud.pojo.Ingredient;

public interface IngredientRepository {
    Iterable<Ingredient> findAll();
    Ingredient findById(String id);
    Ingredient save(Ingredient ingredient);
}
