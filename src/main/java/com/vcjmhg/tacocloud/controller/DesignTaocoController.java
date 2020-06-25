package com.vcjmhg.tacocloud.controller;

import com.vcjmhg.tacocloud.pojo.Order;
import com.vcjmhg.tacocloud.service.IngredientRepository;
import com.vcjmhg.tacocloud.service.TacoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import com.vcjmhg.tacocloud.pojo.Ingredient;
import com.vcjmhg.tacocloud.pojo.Ingredient.Type;
import com.vcjmhg.tacocloud.pojo.Taco;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTaocoController {
    private final IngredientRepository ingredientRepo;
    private TacoRepository designRepo;
    @Autowired
    DesignTaocoController(IngredientRepository ingredientRepo,TacoRepository designRepo){
            this.ingredientRepo=ingredientRepo;
        this.designRepo=designRepo;
    }
    @ModelAttribute(name = "order")
    public Order ordr(){
        return new Order();
    }
    @ModelAttribute(name = "taco")
    public Taco taco(){
        return new Taco();
    }
    @GetMapping
    public String showDesignForm(Model model){
        List<Ingredient> ingredients=new ArrayList<>();
        //将从Ingredient数据库中查询到的所有数据都保存到ingredients中
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
    public String processDesign(@ModelAttribute("design") Taco design, Errors errors,@ModelAttribute Order order) {
        if (errors.hasErrors()) {
            return "design";
        }
        Taco saved=designRepo.save(design);
        order.addDesign(saved);
        log.info("Processing design: " + design);
        return "redirect:/orders/current";
    }
}
