package com.oparin.busbus.Controllers;

import com.oparin.busbus.dao.RouteDaoImpl;
// com.oparin.busbus.dao.UserDao;
//import com.oparin.busbus.dao.UserDaoImpl;
//import com.oparin.busbus.dao.UserService;
import com.oparin.busbus.models.MyUser;
import com.oparin.busbus.models.Route;
//import com.oparin.busbus.models.User;
import com.oparin.busbus.repository.UserRepository;
import com.oparin.busbus.services.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.oparin.busbus.config.MyUserDetails;

import org.springframework.beans.factory.annotation.Autowired;
import com.oparin.busbus.models.MyUser;
//import com.oparin.busbus.dao.UserDaoImpl;
import org.springframework.data.jpa.repository.JpaRepository;



@Controller
@RequestMapping("/users")
public class UserController {

    //private final UserDaoImpl userDao = new UserDaoImpl();

    @Autowired
    private AppService service;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @GetMapping("")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new MyUser()); //изменил User на MyUser
        return "addUser"; // Возвращаем имя представления с формой
    }

    @PostMapping("")
    public ResponseEntity<String> registerNewUser(@RequestParam String name, @RequestParam String password) {
        MyUser newUser = new MyUser();
        newUser.setName(name);
        newUser.setPassword(passwordEncoder.encode(password)); // Хэшируем пароль перед сохранением
        newUser.setRoles("ROLE_USER"); // Устанавливаем роль пользователя
        userRepository.save(newUser);
        return ResponseEntity.ok("User registered successfully");
    }


}