package com.vcjmhg.tacocloud.controller;

import com.vcjmhg.tacocloud.service.IngredientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.vcjmhg.tacocloud.pojo.Ingredient;
import com.vcjmhg.tacocloud.pojo.Ingredient.Type;
import com.vcjmhg.tacocloud.pojo.Taco;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTaocoController {
    private final IngredientRepository ingredientRepo;
    @Autowired
    public DesignTaocoController( IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    @GetMapping
    public String showDesignForm(Model model){
        List<Ingredient> ingredients=new ArrayList<>();
        //ToDo
        ingredientRepo.findAll().forEach(i->ingredients.add(i));

        Type[] types=Ingredient.Type.values();
        for (Type type:types){
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients,type));
        }
        return "design";
    }
    //通过Lamada实现分类
    private List<Ingredient> filterByType(List<Ingredient> ingredients,Type type){
        return ingredients
                .stream()
                .filter(x->x.getType().equals(type))
                .collect(Collectors.toList());
    }

    @PostMapping
    public String processDesign(@ModelAttribute("design") Taco design, Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "design";
        }
        // Save the taco design...
        // We'll do this in chapter 3
        log.info("Processing design: " + design);
        return "redirect:/orders/current";
    }
}
