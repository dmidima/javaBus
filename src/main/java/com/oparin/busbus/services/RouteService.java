package com.oparin.busbus.services;

import com.oparin.busbus.models.Route;
import com.oparin.busbus.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import org.springframework.transaction.annotation.Transactional;


//import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

//package com.oparin.busbus.services;
//
//import com.oparin.busbus.models.Route;
//import com.oparin.busbus.repository.RouteRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//

import org.springframework.transaction.annotation.*;


@Service
public class RouteService {

    //private final RouteRepository routeRepository;

    @Autowired
    private RouteRepository routeRepository;


//    @Transactional(propagation = Propagation.REQUIRED)
//    public void doSomething(int routeNumber, String startPoint, String endPoint, int duration, LocalDateTime departureDatetime, LocalDateTime arrivalDatetime) {
//        Route route = new Route(routeNumber, startPoint, endPoint, duration, Date.from(departureDatetime.atZone(ZoneId.systemDefault()).toInstant()), Date.from(arrivalDatetime.atZone(ZoneId.systemDefault()).toInstant()));
//
//        routeService.addRoute(route);
//
//        int routeId = route.getId();
//
//        Route route2 = routeRepository.findById(routeId).orElse(null);
//        System.out.println(route2.getRouteNumber());
//    }


    //@Transactional(propagation = Propagation.REQUIRED)

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addRoute(Route route) {
        routeRepository.save(route);
    }

    @Transactional(propagation = Propagation.REQUIRED)

    //@Transactional(propagation = Propagation.REQUIRES_NEW)
    public Route getRouteById(int id) {
        return routeRepository.findById(id).orElse(null);
    }


    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

//    public void addRoute(Route route) {
//        routeRepository.save(route);
//    }
//
//    public Route getRouteById(int id) {
//        return routeRepository.findById(id).orElse(null);
//    }


    public void deleteRouteById(int id) {
        routeRepository.deleteRouteById(id);
    }

    public void updateRoute(Route route) {
        routeRepository.save(route);
    }

    public void deleteRoute(Route route) {
        routeRepository.delete(route);
    }

    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    public List<Integer> getAllDurations() {
        return routeRepository.getAllDurations();
    }

    public List<Route> findRoutesByStartAndEndPoint(String startPoint, String endPoint) {
        return routeRepository.findRoutesByStartAndEndPoint(startPoint, endPoint);
    }


}