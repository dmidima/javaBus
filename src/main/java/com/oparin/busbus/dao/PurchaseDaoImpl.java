package com.oparin.busbus.dao;

import com.oparin.busbus.models.Purchase;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PurchaseDaoImpl implements PurchaseDao {

    @Override
    public void addPurchase(Purchase purchase) {
        String query = "INSERT INTO purchase (ticket_id, purchase_date, purchase_status, return_status, buyer_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, purchase.getTicketId());
            statement.setDate(2, new java.sql.Date(purchase.getPurchaseDate().getTime()));
            statement.setBoolean(3, purchase.isPurchaseStatus());
            statement.setBoolean(4, purchase.isReturnStatus());
            statement.setInt(5, purchase.getBuyerId());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Purchase added successfully.");
            } else {
                System.out.println("Failed to add purchase.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Purchase getPurchaseById(int id) {
        String query = "SELECT * FROM purchase WHERE id = ?";
        Purchase purchase = null;
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                purchase = new Purchase(
                        resultSet.getInt("ticket_id"),
                        resultSet.getDate("purchase_date"),
                        resultSet.getBoolean("purchase_status"),
                        resultSet.getBoolean("return_status"),
                        resultSet.getInt("buyer_id")
                );
                purchase.setId(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return purchase;
    }

    @Override
    public void updatePurchase(Purchase purchase) {
        String query = "UPDATE purchase SET ticket_id = ?, purchase_date = ?, purchase_status = ?, return_status = ?, buyer_id = ? WHERE id = ?";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, purchase.getTicketId());
            statement.setDate(2, new java.sql.Date(purchase.getPurchaseDate().getTime()));
            statement.setBoolean(3, purchase.isPurchaseStatus());
            statement.setBoolean(4, purchase.isReturnStatus());
            statement.setInt(5, purchase.getBuyerId());
            statement.setInt(6, purchase.getId());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Purchase updated successfully.");
            } else {
                System.out.println("Failed to update purchase.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePurchase(Purchase purchase) {
        String query = "DELETE FROM purchase WHERE id = ?";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, purchase.getId());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Purchase deleted successfully.");
            } else {
                System.out.println("Failed to delete purchase.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}