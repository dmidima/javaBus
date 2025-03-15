package com.oparin.busbus.dao;

import com.oparin.busbus.models.Personalcabinet;
import java.sql.*;

public class PersonalcabinetDaoImpl implements PersonalcabinetDao {

    @Override
    public void viewTickets() {
        String query = "SELECT ticket_list FROM personalcabinet WHERE id = ?";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            // Assume user's personal cabinet id is obtained and set in the following line
            int personalCabinetId = 1;
            statement.setInt(1, personalCabinetId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String ticketList = resultSet.getString("ticket_list");
                System.out.println("Your ticket list: " + ticketList);
            } else {
                System.out.println("No tickets found in your personal cabinet.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String returnTicket(String ticketNumber) {
        String query = "UPDATE personalcabinet SET ticket_list = REPLACE(ticket_list, ?, '') WHERE id = ?";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            // Assume user's personal cabinet id is obtained and set in the following line
            int personalCabinetId = 1;
            statement.setString(1, ticketNumber);
            statement.setInt(2, personalCabinetId);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                return "Ticket with number " + ticketNumber + " has been returned successfully.";
            } else {
                return "Failed to return the ticket. Please make sure the ticket number is correct.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "An error occurred during ticket return.";
        }
    }
}