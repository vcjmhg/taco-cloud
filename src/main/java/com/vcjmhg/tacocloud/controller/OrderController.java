package com.vcjmhg.tacocloud.controller;

import com.vcjmhg.tacocloud.pojo.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
public class OrderController {
    @GetMapping("/current")
    public String orderForm(Model model){
        model.addAttribute("order",new Order());
        return "orderForm";
    }
    @PostMapping
    public String ProcessOrder(@Valid Order order, Errors errors){
        if(errors.hasErrors()){
            log.error("ProcessOrder has error"+errors.toString());
            return "orderForm";
        }
        log.info("Order submitted:"+order);
        //重定向到根目录
        return "redirect:/";
    }
}
