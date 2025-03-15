package com.oparin.busbus.Controllers;

import com.oparin.busbus.config.MyUserDetails;
import com.oparin.busbus.dao.DbConnection;
import com.oparin.busbus.dao.RouteDaoImpl;
import com.oparin.busbus.dao.TicketDao;
import com.oparin.busbus.dao.TicketDaoImpl;
import com.oparin.busbus.models.*;
import com.oparin.busbus.services.AppService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.oparin.busbus.models.Ticket;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;


import com.oparin.busbus.models.Route;
import com.oparin.busbus.models.Ticket;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.RollbackException;

@Controller

public class TicketController {
    private final RouteDaoImpl routeDao = new RouteDaoImpl();
    private final TicketDaoImpl ticketDao = new TicketDaoImpl();



    @Autowired
    private AppService appService;

//    @PostMapping("/purchaseTicket")
//    @Transactional
//    public String purchaseTicket(@RequestParam("routeId") int routeId,
//                                 @RequestParam("departureFrom") String startPoint,
//                                 @RequestParam("departureTo") String endPoint,
//                                 @RequestParam("duration") int duration,
//                                 @RequestParam("departureDatetime") String departureDatetime,
//                                 @RequestParam("arrivalDatetime") String arrivalDatetime,
//                                 @RequestParam("price") int price,
//                                 @RequestParam(value="seatNumber", required = false) Integer seatNumber,
//                                 Model model) throws ParseException {
//
//        // Получить объект Route по идентификатору
//        Route route = routeDao.getRouteById(routeId);
//
//        // Если объект Route не найден, вернуть сообщение об ошибке или перенаправить на страницу ошибки
//        if (route == null) {
//            // Обработать ошибку
//            return "redirect:/errorPage";
//        }
//
//        // Получить максимальный идентификатор билета из БД
//        int id = ticketDao.max_id();
//
//        // Получить текущего пользователя
//        MyUserDetails currentUser = appService.getCurrentUser();
//
//        // Если пользователь не авторизован, вернуть сообщение об ошибке или перенаправить на страницу ошибки
//        if (currentUser == null) {
//            // Обработать ошибку
//            return "redirect:/errorPage";
//        }
//
//        // Получить объект MyUser из MyUserDetails
//        MyUser user = currentUser.getUser();
//
//
//
//        String modifiedDateString = departureDatetime.replaceFirst("-Wed-", "-");
//        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
//        Date date = sdf.parse(modifiedDateString);
//
//
//
//        // Создать новый объект Ticket
//        Ticket ticket = new Ticket(id, route, user, seatNumber, date, (double) price);
//
//        // Сохранить билет в БД
//        ticketDao.addTicket(ticket);
//
//        // Перенаправить на страницу успеха или вернуть страницу с сообщением об успешной покупке
////        return "profile";
//        return "redirect:/profile";
//    }
    @PostMapping("/purchaseTicket")
    @Transactional
    public String purchaseTicket(@RequestParam("routeId") int routeId,
                                 @RequestParam("departureFrom") String startPoint,
                                 @RequestParam("departureTo") String endPoint,
                                 @RequestParam("duration") int duration,
                                 @RequestParam("departureDatetime") String departureDatetime,
                                 @RequestParam("arrivalDatetime") String arrivalDatetime,
                                 @RequestParam("price") int price,
                                 @RequestParam(value="seatNumber", required = false) Integer seatNumber,
                                 Model model, HttpServletRequest req) throws ParseException, RollbackException {

        // Получить объект Route по идентификатору
        Route route = routeDao.getRouteById(routeId);

        // Если объект Route не найден, вернуть сообщение об ошибке или перенаправить на страницу ошибки
        if (route == null) {
            // Обработать ошибку
            return "redirect:/errorPage";
        }

        // Получить максимальный идентификатор билета из БД
        int id = ticketDao.max_id();

        // Получить текущего пользователя
        MyUserDetails currentUser = appService.getCurrentUser();

        // Если пользователь не авторизован, вернуть сообщение об ошибке или перенаправить на страницу ошибки
        if (currentUser == null) {
            // Обработать ошибку
            return "redirect:/errorPage";
        }

        // Получить объект MyUser из MyUserDetails
        MyUser user = currentUser.getUser();



        String modifiedDateString = departureDatetime.replaceFirst("-Wed-", "-");
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
        Date date = sdf.parse(modifiedDateString);



        // Создать новый объект Ticket
        Ticket ticket = new Ticket(id, route, user, seatNumber, date, (double) price);

        // Сохранить билет в БД
        ticketDao.addTicket(ticket);


        // Установить флаг отката транзакции
        //TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();


        // Вызвать метод возврата билета
        //
        //user_return_ticket(ticket.getTicketNumber());
        // Установить флаг отката транзакции
        //TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        return "redirect:/profile?refresh";
    }

    @PostMapping("/user_return")
    public String user_return_ticket(@RequestParam("ticketId") int ticketId) throws RollbackException {
        String query = "INSERT INTO ticketSost (idTicket, sost) VALUES (?, 0)";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, ticketId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "redirect:/profile?refresh";
    }

    // Метод для отображения страницы с билетами sost 0
    @GetMapping("/dispet_return")
    public String showTickets(Model model) {
        // Здесь можно добавить логику для получения списка билетов из базы данных или другого источника данных
        List<String[]> ticketList = ticketDao.getTicketsBySOST_0(); // Замените на ваш сервис для получения билетов

        MyUserDetails currentUser = appService.getCurrentUser();

        model.addAttribute("hasRoleAdmin", currentUser.isAdmin());

        //List<String[]> ticketList = ticketDao.getTicketsByUserId(userId);
        model.addAttribute("ticketList", ticketList);
        return "dispet";
    }


    @PostMapping("/dispet_return")
    public String dispet_return_ticket(@RequestParam("ticketId") int ticketId) {
        String query = "UPDATE ticketSost SET sost = 1 WHERE idTicket = ?";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, ticketId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "redirect:/dispet_return";
    }


    @PostMapping("/Ticket/buy")
    public String buyTicket(@RequestParam("routeId") int routeId,
                            @RequestParam("startPoint") String startPoint,
                            @RequestParam("endPoint") String endPoint,
                            @RequestParam("duration") int duration,
                            @RequestParam("departureDatetime") String departureDatetime,
                            @RequestParam("arrivalDatetime") String arrivalDatetime,
                            @RequestParam("price") int price,
                            Model model) {

        Route route = routeDao.getRouteById(routeId);

        // Создаем экземпляр класса, содержащего метод getAvailableSeatNumbers
        TicketDaoImpl yourClass = new TicketDaoImpl();

        // Вызываем метод, который хотим протестировать
        List<Integer> result = yourClass.getAvailableSeatNumbers(routeId);

        model.addAttribute("startPoint", startPoint);
        model.addAttribute("endPoint", endPoint);
        model.addAttribute("duration", duration);
        model.addAttribute("departureDatetime", departureDatetime);
        model.addAttribute("arrivalDatetime", arrivalDatetime);
        model.addAttribute("price", price);
        model.addAttribute("route", route);
        model.addAttribute("seats", result);


        return "buyTicket";
    }



    @GetMapping("/Ticket/buy") // Добавьте метод GET
    public String buyTicketForm(Model model) {
        List<Route> routes = routeDao.getAllRoutes();
        model.addAttribute("routes", routes);
        return "buyTicket";
    }

//    @GetMapping("/Ticket/buy")
//    public String viewUserTickets(Model model, @RequestParam("userId") int userId) {
//        List<Route> routes = routeDao.getAllRoutes();
//        List<Ticket> userTickets = List.of(ticketDao.viewTickets(userId));
//        model.addAttribute("routes", routes);
//        model.addAttribute("userTickets", userTickets);
//        return "buyTicket";
//    }

}
