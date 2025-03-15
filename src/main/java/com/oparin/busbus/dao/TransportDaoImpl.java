package com.oparin.busbus.dao;
import com.oparin.busbus.models.Transport;
import java.sql.*;

public class TransportDaoImpl implements TransportDao {

    @Override
    public void addTransport(Transport transport) {
        String query = "INSERT INTO transport (transport_number, car_brand, passenger_capacity, route_id) VALUES (?, ?, ?, ?)";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, transport.getTransportNumber());
            statement.setString(2, transport.getCarBrand());
            statement.setInt(3, transport.getPassengerCapacity());
            statement.setInt(4, transport.getRouteId());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Транспорт успешно добавлен с номером: " + transport.getTransportNumber());
            } else {
                System.out.println("Ошибка при добавлении транспорта.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Transport getTransportById(int id) {
        String query = "SELECT * FROM transport WHERE id = ?";
        Transport transport = null;
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                transport = new Transport(resultSet.getInt("transport_number"),
                        resultSet.getString("car_brand"),
                        resultSet.getInt("passenger_capacity"),
                        resultSet.getInt("route_id"));
                transport.setId(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transport;
    }

    @Override
    public void updateTransport(Transport transport) {
        String query = "UPDATE transport SET transport_number = ?, car_brand = ?, passenger_capacity = ?, route_id = ? WHERE id = ?";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, transport.getTransportNumber());
            statement.setString(2, transport.getCarBrand());
            statement.setInt(3, transport.getPassengerCapacity());
            statement.setInt(4, transport.getRouteId());
            statement.setInt(5, transport.getId());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Транспорт успешно обновлен с номером: " + transport.getTransportNumber());
            } else {
                System.out.println("Ошибка при обновлении транспорта.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTransport(Transport transport) {
        String query = "DELETE FROM transport WHERE id = ?";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, transport.getId());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Транспорт успешно удален с номером: " + transport.getTransportNumber());
            } else {
                System.out.println("Ошибка при удалении транспорта.");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}