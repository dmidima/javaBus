package com.oparin.busbus.dao;

import com.oparin.busbus.models.Route;

import java.util.List;

public interface RouteDao {
    void addRoute(Route route);
    Route getRouteById(int id);
    void updateRoute(Route route);
    void deleteRoute(Route route);

    List<Route> getAllRoutes();



}