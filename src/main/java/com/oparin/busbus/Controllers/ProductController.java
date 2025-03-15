package com.oparin.busbus.Controllers;


import com.oparin.busbus.config.MyUserDetails;
import com.oparin.busbus.models.MyUser;
import com.oparin.busbus.services.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.oparin.busbus.models.MyUser;
import java.util.Date;


//@Controller
//public class ProductController {
//
//    @GetMapping("/")
//    public String products(Model model){
//        model.addAttribute("title", "Главная страница");
//        return "product";
//    }
//
//
//    @Autowired
//    private RouteController routeController;
//
//    @PostMapping("/product")
//    public String searchRoutes(@RequestParam String from, @RequestParam String to, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date, Model model) {
//        // Перенаправление запроса на метод отображения маршрутов в контроллере маршрутов
//        return routeController.showFilteredAndSortedRoutes(from, to, date, "ASC", model);
//    }
//
//}







@Controller
public class ProductController {
    @Autowired
    private AppService appService;
    @Autowired
    private RouteController routeController;

    @GetMapping("/product")
    public String getProductPage(Model model) {
        boolean hasRoleAdmin = checkUserRole();
        String greetingMessage = "";
        MyUserDetails currentUser = appService.getCurrentUser();
        if (currentUser != null) {
            if (currentUser.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
                greetingMessage = "Привет, администратор!";
            } else {
                greetingMessage = "Привет, обычный пользователь!";
            }
        }
        model.addAttribute("hasRoleAdmin", hasRoleAdmin);
        model.addAttribute("title", "Product Page");
        model.addAttribute("greetingMessage", greetingMessage);
        return "product";
    }

    private boolean checkUserRole() {
        MyUserDetails currentUser = appService.getCurrentUser();
        if (currentUser != null) {
            return currentUser.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        return false;
    }



    @PostMapping("/searchRoutes")
    public String searchRoutes(@RequestParam String from, @RequestParam String to, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date, Model model) {
//        return routeController.showFilteredAndSortedRoutes(from, to, date, "ASC", model);
        return routeController.showFilteredAndSortedRoutes(from, to, date, "ASC", 0, 3, model);

    }
}