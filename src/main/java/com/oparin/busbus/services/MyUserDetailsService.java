package com.oparin.busbus.services;

import com.oparin.busbus.models.MyUser;
import com.oparin.busbus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.oparin.busbus.config.MyUserDetails;

import java.util.Optional;
@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser user = repository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
        return new MyUserDetails(user);
    }
}