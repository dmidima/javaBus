package com.oparin.busbus.services;


import com.oparin.busbus.config.MyUserDetails;
import com.oparin.busbus.models.Application;
import com.oparin.busbus.models.MyUser;
//import com.oparin.busbus.models.User;
import com.oparin.busbus.models.Route;
import com.oparin.busbus.repository.RouteRepository;
import com.oparin.busbus.repository.UserRepository;
//import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import org.springframework.transaction.annotation.Transactional;

//import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

@AllArgsConstructor
@Service
public class AppService {

    private List<Application> applications;
    private UserRepository repository;
    private PasswordEncoder passwordEncoder;


    @Autowired
    private RouteService routeService;

    @Autowired
    private RouteRepository routeRepository;


//    @PostConstruct
//    public void loadAppInDB(){
//        Faker faker = new Faker();
//        applications = IntStream.rangeClosed(1, 100)
//                .mapToObj(i -> Application.builder()
//                        .id(i)
//                        .name(faker.app().name())
//                        .author(faker.app().author())
//                        .version(faker.app().version())
//                        .build())
//                .toList();
//    }

    public List<Application> allApplications() {
        return applications;
    }

    public Application applicationByID(int id) {
        return applications.stream()
                .filter(app -> app.getId() == id)
                .findFirst()
                .orElse(null);
    }


    public void addUser(MyUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void doSomething(int routeNumber, String startPoint, String endPoint, int duration, LocalDateTime departureDatetime, LocalDateTime arrivalDatetime) {
        Route route = new Route(routeNumber, startPoint, endPoint, duration, Date.from(departureDatetime.atZone(ZoneId.systemDefault()).toInstant()), Date.from(arrivalDatetime.atZone(ZoneId.systemDefault()).toInstant()));
        routeService.addRoute(route);
        int routeId = route.getId();
        Route route2 = routeRepository.findById(routeId).orElse(null);
        System.out.println(route2.getRouteNumber());
    }








    // Получить текущего вошедшего в систему пользователя
//    public MyUser getCurrentUser() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null && authentication.isAuthenticated()) {
//            return (MyUser) authentication.getPrincipal();
//        }
//        return null;
//    }

//    public MyUserDetails getCurrentUser() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null && authentication.isAuthenticated()) {
//            return (MyUserDetails) authentication.getPrincipal();
//        }
//        return null;
//    }

    // Получить текущего вошедшего в систему пользователя
    public MyUserDetails getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
            return userDetails;
        }
        return null;
    }


}