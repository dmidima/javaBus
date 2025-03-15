package com.oparin.busbus.dao;

import com.oparin.busbus.models.Route;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RouteDaoImpl implements RouteDao {


    @Override
    public void addRoute(Route route) {
        String query = "INSERT INTO route (route_number, start_point, end_point, duration, departure_datetime, arrival_datetime) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, route.getRouteNumber());
            statement.setString(2, route.getStartPoint());
            statement.setString(3, route.getEndPoint());
            statement.setInt(4, route.getDuration());
            statement.setTimestamp(5, new Timestamp(route.getDepartureDatetime().getTime()));
            statement.setTimestamp(6, new Timestamp(route.getArrivalDatetime().getTime()));

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Маршрут успешно добавлен с номером: " + route.getRouteNumber());
            } else {
                System.out.println("Ошибка при добавлении маршрута.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Route getRouteById(int id) {
        String query = "SELECT * FROM route WHERE id = ?";
        Route route = null;
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    route = new Route(
                            resultSet.getInt("route_number"),
                            resultSet.getString("start_point"),
                            resultSet.getString("end_point"),
                            resultSet.getInt("duration"),
                            new Date(resultSet.getTimestamp("departure_datetime").getTime()),
                            new Date(resultSet.getTimestamp("arrival_datetime").getTime())
                    );
                    route.setId(resultSet.getInt("id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return route;
    }

    @Override
    public void updateRoute(Route route) {
        String query = "UPDATE route SET route_number = ?, start_point = ?, end_point = ?, duration = ?, departure_datetime = ?, arrival_datetime = ? WHERE id = ?";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, route.getRouteNumber());
            statement.setString(2, route.getStartPoint());
            statement.setString(3, route.getEndPoint());
            statement.setInt(4, route.getDuration());
            statement.setTimestamp(5, new Timestamp(route.getDepartureDatetime().getTime()));
            statement.setTimestamp(6, new Timestamp(route.getArrivalDatetime().getTime()));
            statement.setInt(7, route.getId());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Маршрут успешно обновлен с номером: " + route.getRouteNumber());
            } else {
                System.out.println("Ошибка при обновлении маршрута.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRoute(Route route) {
        String query = "DELETE FROM route WHERE id = ?";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, route.getId());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Маршрут успешно удален с номером: " + route.getRouteNumber());
            } else {
                System.out.println("Ошибка при удалении маршрута.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Route> getAllRoutes() {
        List<Route> routes = new ArrayList<>();
        String query = "SELECT id, route_number, start_point, end_point, duration, departure_datetime, arrival_datetime FROM route";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Route route = new Route(
                        resultSet.getInt("route_number"),
                        resultSet.getString("start_point"),
                        resultSet.getString("end_point"),
                        resultSet.getInt("duration"),
                        new Date(resultSet.getTimestamp("departure_datetime").getTime()),
                        new Date(resultSet.getTimestamp("arrival_datetime").getTime())
                );
                route.setId(resultSet.getInt("id"));
                routes.add(route);
            }
        } catch (SQLException e) {
            // Вместо простого вывода ошибки, желательно логировать ошибку или обработать ее подробнее.
            e.printStackTrace();
        }
        return routes;
    }

}