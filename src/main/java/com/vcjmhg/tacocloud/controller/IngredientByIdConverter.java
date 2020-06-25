package com.vcjmhg.tacocloud.controller;

import com.vcjmhg.tacocloud.pojo.Ingredient;
import com.vcjmhg.tacocloud.service.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

/**
 * @description
 * 提供一个工具方法，给定id返回对应的Ingredient对象
 * @author vcjmhg
 * @date 2020/6/25 18:28
 * @version ch3
 **/
public class IngredientByIdConverter implements Converter<String,Ingredient> {
    @Autowired
    private IngredientRepository ingredientRep;
    IngredientByIdConverter(IngredientRepository ingredientRep){
        this.ingredientRep=ingredientRep;
    }

    @Override
    public Ingredient convert(String id) {
        return ingredientRep.findById(id);
    }
}
