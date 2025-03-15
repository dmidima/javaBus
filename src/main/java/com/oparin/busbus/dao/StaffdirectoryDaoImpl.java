package com.oparin.busbus.dao;

import com.oparin.busbus.models.Staffdirectory;
import java.sql.*;

public class StaffdirectoryDaoImpl implements StaffdirectoryDao {

    @Override
    public void addStaff(Staffdirectory staff) {
        String query = "INSERT INTO staffdirectory (last_name, first_name, email, job, schedule) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, staff.getLastName());
            statement.setString(2, staff.getFirstName());
            statement.setString(3, staff.getEmail());
            statement.setString(4, staff.getJob());
            statement.setString(5, staff.getSchedule());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Персонал успешно добавлен: " + staff.getLastName() + " " + staff.getFirstName());
            } else {
                System.out.println("Ошибка при добавлении персонала.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Staffdirectory getStaffById(int id) {
        String query = "SELECT * FROM staffdirectory WHERE id = ?";
        Staffdirectory staff = null;
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                staff = new Staffdirectory(
                        resultSet.getString("last_name"),
                        resultSet.getString("first_name"),
                        resultSet.getString("email"),
                        resultSet.getString("job"),
                        resultSet.getString("schedule")
                );
                staff.setId(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staff;
    }

    @Override
    public void updateStaff(Staffdirectory staff) {
        String query = "UPDATE staffdirectory SET last_name = ?, first_name = ?, email = ?, job = ?, schedule = ? WHERE id = ?";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, staff.getLastName());
            statement.setString(2, staff.getFirstName());
            statement.setString(3, staff.getEmail());
            statement.setString(4, staff.getJob());
            statement.setString(5, staff.getSchedule());
            statement.setInt(6, staff.getId());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Информация о персонале успешно обновлена: " + staff.getLastName() + " " + staff.getFirstName());
            } else {
                System.out.println("Ошибка при обновлении информации о персонале.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteStaff(Staffdirectory staff) {
        String query = "DELETE FROM staffdirectory WHERE id = ?";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, staff.getId());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Персонал успешно удален: " + staff.getLastName() + " " + staff.getFirstName());
            } else {
                System.out.println("Ошибка при удалении информации о персонале.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}