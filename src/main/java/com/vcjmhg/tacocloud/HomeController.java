package com.vcjmhg.tacocloud;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Administrator
 */
@Controller
public class HomeController {
    @GetMapping("/")
    public String home(){
        return "home";
    }
}
