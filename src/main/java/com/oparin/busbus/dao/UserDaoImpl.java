//package com.oparin.busbus.dao;
//
//import com.oparin.busbus.dao.UserDao;
//import com.oparin.busbus.models.User;
//
//import java.sql.*;
//
//public class UserDaoImpl implements UserDao {
//
//    public void addUser(User user) {
//        String query = "INSERT INTO user (first_name, last_name , email, age, password, role) VALUES (?, ?, ?, ?, ?, ?)";
//
//        try (Connection connection = DbConnection.getConnection();
//             PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.setString(1, user.getFirstName());
//            statement.setString(2, user.getLastName());
//            statement.setString(3, user.getEmail());
//            statement.setInt(4, user.getAge());
//            statement.setString(5, user.getPassword());
//            statement.setString(6, user.getRole());
//
//            int rowsAffected = statement.executeUpdate();
//            if (rowsAffected > 0) {
//                System.out.println("Пользователь успешно добавлен: " + user.getEmail());
//            } else {
//                System.out.println("Ошибка при добавлении пользователя.");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public User getUserByEmail(String email) {
//        String query = "SELECT * FROM user WHERE email = ?";
//        User user = null;
//
//        try (Connection connection = DbConnection.getConnection();
//             PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.setString(1, email);
//            ResultSet resultSet = statement.executeQuery();
//
//            if (resultSet.next()) {
//                user = new User(resultSet.getString("first_name"), resultSet.getString("last_name"),
//                        resultSet.getString("email"), resultSet.getInt("age"),
//                        resultSet.getString("password"), resultSet.getString("role"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return user;
//    }
//
//    public void updateUser(User user) {
//        String query = "UPDATE user SET first_name = ?, last_name = ?, age = ?, password = ?, role = ? WHERE email = ?";
//
//        try (Connection connection = DbConnection.getConnection();
//             PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.setString(1, user.getFirstName());
//            statement.setString(2, user.getLastName());
//            statement.setInt(3, user.getAge());
//            statement.setString(4, user.getPassword());
//            statement.setString(5, user.getRole());
//            statement.setString(6, user.getEmail());
//
//            int rowsAffected = statement.executeUpdate();
//            if (rowsAffected > 0) {
//                System.out.println("Пользователь успешно обновлен: " + user.getEmail());
//            } else {
//                System.out.println("Ошибка при обновлении пользователя.");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void deleteUser(User user) {
//        String query = "DELETE FROM user WHERE email = ?";
//
//        try (Connection connection = DbConnection.getConnection();
//             PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.setString(1, user.getEmail());
//
//            int rowsAffected = statement.executeUpdate();
//            if (rowsAffected > 0) {
//                System.out.println("Пользователь успешно удален: " + user.getEmail());
//            } else {
//                System.out.println("Ошибка при удалении пользователя.");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}
