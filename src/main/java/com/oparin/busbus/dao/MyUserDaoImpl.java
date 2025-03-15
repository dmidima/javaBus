//package com.oparin.busbus.dao;
//
//import com.oparin.busbus.models.Buyer;
//import com.oparin.busbus.models.MyUser;
//import com.oparin.busbus.models.Ticket;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class MyUserDaoImpl implements BuyerDao {
//
//    private List<MyUser> buyers = new ArrayList<>();
//
//    @Override
//    public void addBuyer(MyUser buyer) {
//        buyers.add(buyer);
//        System.out.println("Покупатель успешно добавлен: " + buyer.getEmail());
//    }
//
//    @Override
//    public boolean buyTicket(MyUser buyer) {
//        // Логика покупки билета (можно добавить какую-то реальную логику)
//        System.out.println("Билет успешно куплен для: " + buyer.getEmail());
//        return true; // Возвращаем успешность операции покупки
//    }
//
//    @Override
//    public boolean returnTicket(MyUser buyer) {
//        // Логика возврата билета (можно добавить какую-то реальную логику)
//        System.out.println("Билет успешно возвращен для: " + buyer.getEmail());
//        return true; // Возвращаем успешность операции возврата
//    }
//
//    @Override
//    public Ticket[] viewTickets(MyUser buyer) {
//        List<Ticket> tickets = new ArrayList<>();
//
//        String query = "SELECT * FROM ticket WHERE IdПокупателя = ?";
//
//        try (Connection connection = DbConnection.getConnection();
//             PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.setInt(1, buyer.getId());
//            ResultSet resultSet = statement.executeQuery();
//
//            while (resultSet.next()) {
//                Ticket ticket = new Ticket(
//                        resultSet.getInt("ticket_id"),
//                        null, // Предположим, что у вас нет объекта Route из ResultSet
//                        buyer,
//                        resultSet.getInt("seatNumber"), // Предположим, что seatNumber - int
//                        resultSet.getDate("departureDateTime"), // Используем getDate для получения java.util.Date
//                        resultSet.getDouble("price")
//                );
//                tickets.add(ticket);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return tickets.toArray(new Ticket[0]);
//    }
//
//}
