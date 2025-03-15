package com.oparin.busbus.Controllers;

import com.oparin.busbus.config.MyUserDetails;
import com.oparin.busbus.models.MyUser;
//import com.oparin.busbus.models.User;
import com.oparin.busbus.repository.UserRepository;
import com.oparin.busbus.services.AppService;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;
import com.oparin.busbus.dao.TicketDaoImpl;
import com.oparin.busbus.dao.TicketDaoImpl;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ProfileController {

//    @GetMapping("/profile")
//    public String showProfilePage() {
//        return "profile"; // Это предполагает, что ваш файл HTML называется "profile.html" и находится в папке resources/templates
//    }
//
//    @GetMapping("/main")
//    public String showMainPage() {
//        return "main"; // Изменен URL для избежания конфликта с ProductController
//    }




    @Autowired
    private AppService service;

//    @GetMapping("/profile")
//    public String showProfilePage(@RequestParam int userId, Model model) {
//
//
//
//        MyUserDetails user = service.getCurrentUser();
//        model.addAttribute("user", user);
//
//
//        TicketDaoImpl ticketDao = new TicketDaoImpl();
//
//        // Получаем список билетов по идентификатору пользователя
//        List<String[]> ticketList = ticketDao.getTicketsByUserId(userId);
//
//        // Передаем список билетов в модель
//        model.addAttribute("ticketList", ticketList);
//
//
//        return "profile";
//    }


//    @GetMapping("/profile")
//    public String showProfilePage(@RequestParam int userId, Model model) {
//        MyUserDetails user = service.getCurrentUser();
//        model.addAttribute("user", user);
//
//
//        TicketDaoImpl ticketDao = new TicketDaoImpl();
//
//        // Получаем список билетов по идентификатору пользователя
//        List<String[]> ticketList = ticketDao.getTicketsByUserId(userId);
//
//        // Передаем список билетов в модель
//        model.addAttribute("ticketList", ticketList);
//
//
//        return "profile";
//    }
    @Autowired
    private AppService appService;

    @GetMapping("/profile")
    public String showProfilePage(Model model) {
        MyUserDetails user = service.getCurrentUser();
        model.addAttribute("user", user);
        int userId = Math.toIntExact(user.getId()); // Получаем ID пользователя
        TicketDaoImpl ticketDao = new TicketDaoImpl();
        MyUserDetails currentUser = appService.getCurrentUser();

        // Получаем список билетов по идентификатору пользователя
        List<String[]> ticketList = ticketDao.getTicketsByUserId(userId);
        // Передаем список билетов в модель
        model.addAttribute("ticketList", ticketList);
        model.addAttribute("hasRoleAdmin", currentUser.isAdmin());
        return "profile";
    }


    @LazyCollection(LazyCollectionOption.FALSE)
    private List<String[]> ticketList;


}


