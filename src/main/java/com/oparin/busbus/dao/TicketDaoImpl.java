package com.oparin.busbus.dao;

//import com.oparin.busbus.models.Buyer;
import com.oparin.busbus.models.Ticket;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TicketDaoImpl implements TicketDao {
    // Реализация методов интерфейса TicketDao

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addTicket(Ticket ticket) {
        String query = "INSERT INTO ticket (ticket_number, route_id, buyer_id, seat_number, departure_date, price) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, ticket.getTicketNumber());
            statement.setInt(2, ticket.getRoute().getId());
            statement.setInt(3, ticket.getBuyer().getId());
            statement.setInt(4, ticket.getSeatNumber());
            statement.setTimestamp(5, new java.sql.Timestamp(ticket.getDepartureDateTime().getTime()));
            statement.setDouble(6, ticket.getPrice());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean deleteTicket(int ticketNumber) {
        String query = "DELETE FROM ticket WHERE ticket_number = ?";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, ticketNumber);

            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Ticket[] viewTickets(int userId) {
        return new Ticket[0];
    }



    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Integer> getAvailableSeatNumbers(int routeId) {
        List<Integer> availableSeats = new ArrayList<>();

        try (Connection conn = DbConnection.getConnection()) {
            String sqlQuery = "SELECT number " +
                    "FROM ( " +
                    "    SELECT 1 AS number " +
                    "    UNION SELECT 2 " +
                    "    UNION SELECT 3 " +
                    "    UNION SELECT 4 " +
                    "    UNION SELECT 5 " +
                    "    UNION SELECT 6 " +
                    "    UNION SELECT 7 " +
                    "    UNION SELECT 8 " +
                    "    UNION SELECT 9 " +
                    "    UNION SELECT 10 " +
                    "    UNION SELECT 11 " +
                    "    UNION SELECT 12 " +
                    "    UNION SELECT 13 " +
                    "    UNION SELECT 14 " +
                    "    UNION SELECT 15 " +
                    "    UNION SELECT 16 " +
                    ") AS all_numbers " +
                    "WHERE all_numbers.number NOT IN (SELECT seat_number FROM ticket WHERE route_id = ?) " +
                    "OR all_numbers.number IN " +
                    "           (SELECT seat_number " +
                    "               FROM ticket" +
                    "               JOIN ticketSost ON ticket.ticket_number = ticketSost.idTicket " +
                    "               WHERE ticket.route_id = ? AND ticketSost.sost = 1)";

            PreparedStatement statement = conn.prepareStatement(sqlQuery);
            statement.setInt(1, routeId);
            statement.setInt(2, routeId);

            ResultSet resultSet = statement.executeQuery();
            System.out.println(resultSet);

            while (resultSet.next()) {
                int seatNumber = resultSet.getInt("number");
                availableSeats.add(seatNumber);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return availableSeats;
    }

    //@Transactional(propagation = Propagation.REQUIRES_NEW)
    public int max_id() {
        int maxId = 0;

        try (Connection conn = DbConnection.getConnection()) {
            String sqlQuery = "SELECT MAX(id) AS max_id FROM ticket;";

            PreparedStatement statement = conn.prepareStatement(sqlQuery);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                maxId = resultSet.getInt("max_id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return maxId;
    }


    //@Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<String[]> getTicketsByUserId(int userId) {
        List<String[]> ticketList = new ArrayList<>();

        try (Connection conn = DbConnection.getConnection()) {
            String sqlQuery = "SELECT "
                    + "t.ticket_number, "
                    + "r.route_number, "
                    + "r.start_point, "
                    + "r.end_point, "
                    + "r.duration, "
                    + "r.departure_datetime, "
                    + "r.arrival_datetime, "
                    + "t.seat_number, "
                    + "t.price "
                    + "FROM users AS u "
                    + "JOIN ticket AS t "
                    + "ON u.id = t.buyer_id "
                    + "JOIN route AS r "
                    + "ON t.route_id = r.id "
                    + "WHERE "
                    + "u.id = ?;";




            PreparedStatement statement = conn.prepareStatement(sqlQuery);
            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Получаем значения столбцов из результата запроса

                String ticket_number = resultSet.getString("ticket_number");
                String route_number = resultSet.getString("route_number");

                String startPoint = resultSet.getString("start_point");
                String endPoint = resultSet.getString("end_point");


                String duration = resultSet.getString("duration");

                String departure_datetime = resultSet.getString("departure_datetime");
                String arrival_datetime = resultSet.getString("arrival_datetime");
                String seat_number = resultSet.getString("seat_number");
                String price = resultSet.getString("price");



                String ticketStatus = "";
                String ticketSostQuery = "SELECT sost FROM ticketSost WHERE idTicket = ?";
                PreparedStatement sostStatement = conn.prepareStatement(ticketSostQuery);
                sostStatement.setInt(1, Integer.parseInt(ticket_number));
                ResultSet sostResultSet = sostStatement.executeQuery();
                if (!sostResultSet.next()) {
                    ticketStatus = "Куплен";
                } else {
                    boolean sost = sostResultSet.getBoolean("sost");
                    if (!sost) {
                        ticketStatus = "Возврат на рассмотрении";
                    } else {
                        ticketStatus = "Билет возвращен";
                    }
                }

                String[] ticketInfo = {ticket_number, route_number, startPoint, endPoint, duration, departure_datetime, arrival_datetime, seat_number, price, ticketStatus};




                // Создаем массив для хранения информации о билете
//                String[] ticketInfo = {ticket_number, route_number, startPoint, endPoint, duration, departure_datetime, arrival_datetime, seat_number, price};

                // Добавляем массив с информацией о билете в список
                ticketList.add(ticketInfo);
            }

            // Закрываем результат запроса и оператор
            resultSet.close();
            statement.close();

            return ticketList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    //@Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<String[]> getTicketsBySOST_0() {
        List<String[]> ticketList = new ArrayList<>();

        try (Connection conn = DbConnection.getConnection()) {
            String sqlQuery = "SELECT "
                    + "t.ticket_number, "
                    + "r.route_number, "
                    + "r.start_point, "
                    + "r.end_point, "
                    + "r.duration, "
                    + "r.departure_datetime, "
                    + "r.arrival_datetime, "
                    + "t.seat_number, "
                    + "t.price "
                    + "FROM ticket AS t "
                    + "JOIN route AS r "
                    + "ON t.route_id = r.id "
                    + "JOIN ticketSost AS ts "
                    + "ON t.ticket_number = ts.idTicket "
                    + "WHERE ts.sost = 0;";
            PreparedStatement statement = conn.prepareStatement(sqlQuery);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String[] ticketData = new String[9];
                ticketData[0] = resultSet.getString("ticket_number");
                ticketData[1] = resultSet.getString("route_number");
                ticketData[2] = resultSet.getString("start_point");
                ticketData[3] = resultSet.getString("end_point");
                ticketData[4] = resultSet.getString("duration");
                ticketData[5] = resultSet.getString("departure_datetime");
                ticketData[6] = resultSet.getString("arrival_datetime");
                ticketData[7] = resultSet.getString("seat_number");
                ticketData[8] = resultSet.getString("price");
                ticketList.add(ticketData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Обработка ошибок
        }

        return ticketList;
    }


    @Override
    public boolean ticketExists(int ticketNumber) {
        String query = "SELECT COUNT(*) AS count FROM ticket WHERE ticket_number = ?";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, ticketNumber);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            int count = resultSet.getInt("count");
            return count > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



}
