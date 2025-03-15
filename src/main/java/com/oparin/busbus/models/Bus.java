//package com.oparin.busbus.models;
//
//import lombok.Data;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//
//import javax.persistence.*;
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.List;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@Entity
//@Table(name = "buses")
//public class Bus {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//    private String model;
//    private String numberPlate;
//    @ManyToMany(mappedBy = "buses")
//    private Set<Route> routes;
//    // Геттеры и сеттеры
//}