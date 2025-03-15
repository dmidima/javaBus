//package com.oparin.busbus.dao;
//
//import com.oparin.busbus.models.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserServiceImpl implements UserService {
//    @Autowired
//    private UserRepository userRepository; // Используйте интерфейс UserRepository здесь
//
//    @Override
//    public boolean authenticateUser(String email, String password) {
//        User user = userRepository.findByEmail(email);
//        if (user != null && user.getPassword().equals(password)) {
//            return true;
//        }
//        return false;
//    }
//}