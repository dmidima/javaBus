package com.oparin.busbus.Controllers;
//import com.oparin.busbus.Service.RouteService;
import com.oparin.busbus.config.MyUserDetails;
import com.oparin.busbus.dao.RouteDao;
import com.oparin.busbus.models.Route;
import com.oparin.busbus.dao.RouteDaoImpl;
import com.oparin.busbus.services.AppService;
import com.oparin.busbus.services.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/routes")
//@Secured("ADMIN")
//@PreAuthorize("hasRole('ADMIN')")
public class RouteController {

//    private final RouteService routeService;
//    @Autowired
//    public RouteController(RouteService routeService) {
//        this.routeService = routeService;
//    }
//
//    @GetMapping("")
//    public String showRoutes(Model model) {
//        List<Route> routes = routeService.getAllRoutes();
//        model.addAttribute("routes", routes);
//        return "routes";
//    }

    @Autowired
    private AppService appService;

    @Autowired
    private RouteService routeService;

    private final RouteDaoImpl routeDao = new RouteDaoImpl();

    @GetMapping("")
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_USER')")
    public String showFilteredAndSortedRoutes(@RequestParam(required = false) String from,
                                              @RequestParam(required = false) String to,
                                              @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
                                              @RequestParam(required = false, defaultValue = "ASC") String order,
                                              @RequestParam(required = false, defaultValue = "0") int page,
                                              @RequestParam(required = false, defaultValue = "3") int size,
                                              Model model) {
        //List<Route> routes = routeDao.getAllRoutes();
        List<Route> routes = routeService.getAllRoutes();

        // Применение фильтров для формы покупки билетов
        if (from != null && !from.isEmpty() && to != null && !to.isEmpty() && date != null) {
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            routes = routes.stream()
                    .filter(route -> route.getStartPoint().equalsIgnoreCase(from))
                    .filter(route -> route.getEndPoint().equalsIgnoreCase(to))
                    .filter(route -> route.getDepartureDatetime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isEqual(localDate))
                    .collect(Collectors.toList());
        }
        // Фильтрация по дате для формы списка маршрутов
        if (date != null) {
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            routes = routes.stream()
                    .filter(route -> route.getDepartureDatetime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isEqual(localDate))
                    .collect(Collectors.toList());
        }
        // Пагинация
        int totalPages = (int) Math.ceil((double) routes.size() / size);
        if (page > totalPages) {
            page = totalPages;
        }
        if (page < 0) {
            page = 0;
        }
        List<Route> pagedRoutes = routes.subList(page * size, Math.min(routes.size(), (page + 1) * size));
        // Сортировка
        if ("ASC".equals(order)) {
            pagedRoutes.sort(Comparator.comparingInt(Route::getDuration));
        } else if ("DESC".equals(order)) {
            pagedRoutes.sort(Comparator.comparingInt(Route::getDuration).reversed());
        }
        model.addAttribute("routes", pagedRoutes);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        String greetingMessage = "";
        MyUserDetails currentUser = appService.getCurrentUser();
        if (currentUser != null) {
            if (currentUser.isAdmin()) {
                greetingMessage = "Привет, администратор!";
            } else {
                greetingMessage = "Привет, обычный пользователь!";
            }
        }
        model.addAttribute("hasRoleAdmin", currentUser.isAdmin());
        model.addAttribute("title", "Product Page");
        model.addAttribute("greetingMessage", greetingMessage);
        return "routes";
    }





//    @PostMapping("")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public String addRoute(@RequestParam int routeNumber,
//                           @RequestParam String startPoint,
//                           @RequestParam String endPoint,
//                           @RequestParam int duration,
//                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime departureDatetime,
//                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime arrivalDatetime) {
//        Route route = new Route(routeNumber, startPoint, endPoint, duration, Date.from(departureDatetime.atZone(ZoneId.systemDefault()).toInstant()), Date.from(arrivalDatetime.atZone(ZoneId.systemDefault()).toInstant()));
//
//        routeService.addRoute(route);
//
//        return "redirect:/routes";
//    }


    @PostMapping("")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addRoute(@RequestParam int routeNumber,
                           @RequestParam String startPoint,
                           @RequestParam String endPoint,
                           @RequestParam int duration,
                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime departureDatetime,
                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime arrivalDatetime) {
        appService.doSomething(routeNumber, startPoint, endPoint, duration, departureDatetime, arrivalDatetime);

        return "redirect:/routes";
    }



    @PostMapping("/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")

    public String deleteRoute(@RequestParam int routeId) {
        Route route = routeDao.getRouteById(routeId);
        if (route != null) {
            //routeDao.deleteRoute(route);
            routeService.deleteRoute(route);
            return "redirect:/routes";
        } else {
            // Handle case where route is not found
            return "redirect:/routes";
        }
    }







//    @PostMapping("/edit")
//    public String editRoute(@RequestParam int routeId, Model model) {
//        Route route = routeDao.getRouteById(routeId);
//        if (route != null) {
//            model.addAttribute("route", route);
//            return "edit-route"; // Создайте страницу edit-route.html для редактирования маршрута
//        } else {
//            // Handle case where route is not found
//            return "redirect:/routes";
//        }
//    }

    @PostMapping("/edit")
    public String editRoute(@RequestParam int routeId, Model model) {
        //Route route = routeDao.getRouteById(routeId);
        Route route = routeService.getRouteById(routeId);
        if (route != null) {
            model.addAttribute("route", route);
            return "edit-route";
        } else {
            // Handle case where route is not found
            return "redirect:/routes";
        }
    }


    @PostMapping("/update")
    public String updateRoute(@ModelAttribute("route") Route route) {
        if (route != null) {
            // Ваши проверки и логика обновления маршрута
            // route.getDepartureDatetime() и route.getArrivalDatetime() должны быть уже в формате Date
//            routeDao.updateRoute(route);
            routeService.updateRoute(route);
            return "redirect:/routes";
        } else {
            // Handle case where route object is not found
            return "redirect:/routes";
        }
    }

    @PostMapping("/{routeId}/update")
    public String updateRouteById(@PathVariable int routeId,
                                  @RequestParam int routeNumber,
                                  @RequestParam String startPoint,
                                  @RequestParam String endPoint,
                                  @RequestParam int duration,
                                  @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime departureDatetime,
                                  @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime arrivalDatetime) {
        //Route route = routeDao.getRouteById(routeId);
        Route route = routeService.getRouteById(routeId);
        if (route != null) {
            route.setRouteNumber(routeNumber);
            route.setStartPoint(startPoint);
            route.setEndPoint(endPoint);
            route.setDuration(duration);
            route.setDepartureDatetime(Date.from(departureDatetime.atZone(ZoneId.systemDefault()).toInstant()));
            route.setArrivalDatetime(Date.from(arrivalDatetime.atZone(ZoneId.systemDefault()).toInstant()));
            routeDao.updateRoute(route);
            return "redirect:/routes";
        } else {
            return "redirect:/routes";
        }
    }




    @GetMapping("/edit")
    public String showEditRouteForm(@RequestParam int routeId, Model model) {
        //Route route = routeDao.getRouteById(routeId);
        Route route = routeService.getRouteById(routeId);
        if (route != null) {
            model.addAttribute("route", route);
            return "edit-route";
        } else {
            // Handle case where route is not found
            return "redirect:/routes";
        }
    }

    @PostMapping("/details")
    public String showRouteDetails(@RequestParam int routeId, Model model) {
        //Route route = routeDao.getRouteById(routeId);
        Route route = routeService.getRouteById(routeId);
        if (route != null) {
            model.addAttribute("route", route);
            return "route-details";
        } else {
            // Обработка случая, если маршрут не найден
            return "redirect:/routes";
        }
    }

//    @GetMapping("/Vladimir-Alexandrov")
//    public String showVladimirAlexandrovRoute(Model model) {
//        List<Route> routes = routeDao.getAllRoutes().stream()
//                .filter(route -> route.getStartPoint().equals("Владимир") && route.getEndPoint().equals("Александров"))
//                .collect(Collectors.toList());
//        model.addAttribute("routes", routes);
//        return "PodrobRoute";
//    }
//
//    @GetMapping("/Vladimir-Vyazniki")
//    public String showVladimirVyaznikiRoute(Model model) {
//        List<Route> routes = routeDao.getAllRoutes().stream()
//                .filter(route -> route.getStartPoint().equals("Владимир") && route.getEndPoint().equals("Вязники"))
//                .collect(Collectors.toList());
//        model.addAttribute("routes", routes);
//        return "PodrobRoute";
//    }
//
//    @GetMapping("/Vladimir-Gorokhovets")
//    public String showVladimirGorokhovetsRoute(Model model) {
//        List<Route> routes = routeDao.getAllRoutes().stream()
//                .filter(route -> route.getStartPoint().equals("Владимир") && route.getEndPoint().equals("Гороховец"))
//                .collect(Collectors.toList());
//        model.addAttribute("routes", routes);
//        return "PodrobRoute";
//    }
//
//
//
//
//    @GetMapping("/Vladimir-Kameshkovo")
//    public String showVladimirKameshkovoRoute(Model model) {
//        List<Route> routes = routeDao.getAllRoutes().stream()
//                .filter(route -> route.getStartPoint().equals("Владимир") && route.getEndPoint().equals("Камешково"))
//                .collect(Collectors.toList());
//        model.addAttribute("routes", routes);
//        return "PodrobRoute";
//    }
//
//
//    @GetMapping("/Vladimir-Kirzhach")
//    public String showVladimirKirzhachRoute(Model model) {
//        List<Route> routes = routeDao.getAllRoutes().stream()
//                .filter(route -> route.getStartPoint().equals("Владимир") && route.getEndPoint().equals("Киржач"))
//                .collect(Collectors.toList());
//        model.addAttribute("routes", routes);
//        return "PodrobRoute";
//    }
//
//
//    @GetMapping("/Vladimir-Kovrov")
//    public String showVladimirKovrovRoute(Model model) {
//        List<Route> routes = routeDao.getAllRoutes().stream()
//                .filter(route -> route.getStartPoint().equals("Владимир") && route.getEndPoint().equals("Ковров"))
//                .collect(Collectors.toList());
//        model.addAttribute("routes", routes);
//        return "PodrobRoute";
//    }
//
//
//    @GetMapping("/Vladimir-Kolchugino")
//    public String showVladimirKolchuginoRoute(Model model) {
//        List<Route> routes = routeDao.getAllRoutes().stream()
//                .filter(route -> route.getStartPoint().equals("Владимир") && route.getEndPoint().equals("Кольчугино"))
//                .collect(Collectors.toList());
//        model.addAttribute("routes", routes);
//        return "PodrobRoute";
//    }
//
//
//    @GetMapping("/Vladimir-Murom")
//    public String showVladimirMuromRoute(Model model) {
//        List<Route> routes = routeDao.getAllRoutes().stream()
//                .filter(route -> route.getStartPoint().equals("Владимир") && route.getEndPoint().equals("Муром"))
//                .collect(Collectors.toList());
//        model.addAttribute("routes", routes);
//        return "PodrobRoute";
//    }
//
//
//    @GetMapping("/Vladimir-Petushki")
//    public String showVladimirPetushkiRoute(Model model) {
//        List<Route> routes = routeDao.getAllRoutes().stream()
//                .filter(route -> route.getStartPoint().equals("Владимир") && route.getEndPoint().equals("Петушки"))
//                .collect(Collectors.toList());
//        model.addAttribute("routes", routes);
//        return "PodrobRoute";
//    }
//
//    @GetMapping("/Vladimir-Sudogda")
//    public String showVladimirSudogdaRoute(Model model) {
//        List<Route> routes = routeDao.getAllRoutes().stream()
//                .filter(route -> route.getStartPoint().equals("Владимир") && route.getEndPoint().equals("Судогда"))
//                .collect(Collectors.toList());
//        model.addAttribute("routes", routes);
//        return "PodrobRoute";
//    }
//
//    @GetMapping("/Vladimir-Suzdal")
//    public String showVladimirSuzdalRoute(Model model) {
//        List<Route> routes = routeDao.getAllRoutes().stream()
//                .filter(route -> route.getStartPoint().equals("Владимир") && route.getEndPoint().equals("Суздаль"))
//                .collect(Collectors.toList());
//        model.addAttribute("routes", routes);
//        return "PodrobRoute";
//    }
//
//    @GetMapping("/Vladimir-Yuriev")
//    public String showVladimirYurievRoute(Model model) {
//        List<Route> routes = routeDao.getAllRoutes().stream()
//                .filter(route -> route.getStartPoint().equals("Владимир") && route.getEndPoint().equals("Юрьев"))
//                .collect(Collectors.toList());
//        model.addAttribute("routes", routes);
//        return "PodrobRoute";
//    }


//    @GetMapping("/routes/{start}-{end}")
//    public String showRoute(@PathVariable String start, @PathVariable String end, Model model) {
//        //List<Route> routes = routeDao.getAllRoutes().stream()
//        List<Route> routes = routeService.getAllRoutes().stream()
//                .filter(route -> route.getStartPoint().equals(start) && route.getEndPoint().equals(end))
//                .collect(Collectors.toList());
//
//        model.addAttribute("routes", routes);
//        return "PodrobRoute";
//    }
//
//    @GetMapping("/allRoutes")
//    public String showAllRoutes(Model model) {
//        //List<Integer> durations = routeDao.getAllRoutes().stream()
//        List<Integer> durations = routeService.getAllRoutes().stream()
//                .map(Route::getDuration)
//                .collect(Collectors.toList());
//        model.addAttribute("durations", durations);
//        return "AllRoutes";
//    }

//    @GetMapping("/sortDurations")
//    public String sortDurations(Model model) {
//        //List<Integer> sortedDurations = routeDao.getAllRoutes().stream()
//        List<Integer> sortedDurations = routeService.getAllRoutes().stream()
//                .map(Route::getDuration)
//                .sorted()
//                .collect(Collectors.toList());
//        model.addAttribute("durations", sortedDurations);
//        return "SortedDurations";
//    }

    @GetMapping("/durations")
    public List<Integer> getAllDurations() {
        return routeService.getAllDurations();
    }

    @GetMapping("/routes")
    public List<Route> findRoutesByStartAndEndPoint(@RequestParam String startPoint, @RequestParam String endPoint) {
        return routeService.findRoutesByStartAndEndPoint(startPoint, endPoint);
    }


}
