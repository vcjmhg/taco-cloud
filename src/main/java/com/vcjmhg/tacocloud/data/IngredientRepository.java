package com.vcjmhg.tacocloud.data;

import com.vcjmhg.tacocloud.Ingredient;
import org.springframework.data.repository.CrudRepository;


public interface IngredientRepository 
         extends CrudRepository<Ingredient, String> {

}
