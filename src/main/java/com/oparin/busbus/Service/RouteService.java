//package com.oparin.busbus.Service;
//
//import com.oparin.busbus.dao.RouteDaoImpl;
//import com.oparin.busbus.models.Route;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class RouteService {
//    private final RouteDaoImpl routeDao = new RouteDaoImpl();
//
//    public List<Route> getAllRoutes() {
//        return routeDao.getAllRoutes();
//    }
//
//    public void addRoute(Route route) {
//        routeDao.addRoute(route);
//    }
//
//    public void updateRoute(int id, Route route) {
//        route.setId(id);
//        routeDao.updateRoute(route);
//    }
//
//    public void deleteRoute(int id) {
//        Route route = routeDao.getRouteById(id);
//        routeDao.deleteRoute(route);
//    }
//}