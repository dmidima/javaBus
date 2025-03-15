package com.oparin.busbus.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

    @GetMapping("/logout")
    public String logout() {
        // Здесь можно добавить логику для выполнения выхода пользователя
        return "redirect:/login"; // Перенаправление на страницу входа после выхода
    }



}
