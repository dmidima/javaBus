package com.oparin.busbus.Controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ProductController {

    @GetMapping("/")
    public String products(Model model){
        model.addAttribute("title", "Главная страница");
        return "product";
    }
}