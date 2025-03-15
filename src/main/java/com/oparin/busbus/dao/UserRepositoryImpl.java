//package com.oparin.busbus.dao;
//
//
//
//import com.oparin.busbus.models.User;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class UserRepositoryImpl implements UserDao, UserRepository {
//
//    private List<User> users;
//
//    public UserRepositoryImpl() {
//        this.users = new ArrayList<>();
//    }
//
//    @Override
//    public void addUser(User user) {
//        users.add(user);
//    }
//
//    @Override
//    public User getUserById(int id) {
//        return null;
//    }
//
//    @Override
//    public List<User> getAllUsers() {
//        return null;
//    }
//
//    @Override
//    public User getUserByEmail(String email) {
//        for (User user : users) {
//            if (user.getEmail().equals(email)) {
//                return user;
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public void updateUser(User user) {
//        for (int i = 0; i < users.size(); i++) {
//            if (users.get(i).getEmail().equals(user.getEmail())) {
//                users.set(i, user);
//                return;
//            }
//        }
//    }
//
//    @Override
//    public void deleteUser(int id) {
//
//    }
//
//    @Override
//    public User findByEmail(String email) {
//        return null;
//    }
//
//    @Override
//    public void deleteUser(User user) {
//        users.remove(user);
//    }
//}