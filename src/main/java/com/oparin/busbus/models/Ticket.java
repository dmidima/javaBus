package com.oparin.busbus.models;

import org.springframework.stereotype.Component;
//import jakarta.persistence.*;
//import lombok.Data;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import java.util.Date;

@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_number", nullable = false)
    private int ticketNumber;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "route_id")

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

//    private Buyer buyer;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "buyer_id")
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")


    @ManyToOne
    @JoinColumn(name = "user_id")
    private MyUser user;



    @Column(name = "seat_number", nullable = false)
    private int seatNumber;
//    @Column(name = "departure_datetime", nullable = false)


    @Column(name = "departure_date_time", nullable = true)
    private Date departureDateTime;

    @Column(name = "price", nullable = false)
    private double price;

//    public Ticket(int ticketNumber, Route route, Buyer buyer, int seatNumber, Date departureDateTime, double price) {
//        this.ticketNumber = ticketNumber;
//        this.route = route;
//        this.buyer = buyer;
//        this.seatNumber = seatNumber;
//        this.departureDateTime = departureDateTime;
//        this.price = price;
//    }

//    public Ticket(int ticketNumber, Route route, Buyer buyer, int seatNumber, Date departureDateTime, double price) {
//        this.ticketNumber = ticketNumber;
//        this.route = route != null ? route : new Route();
//        this.buyer = buyer;
//        this.seatNumber = seatNumber;
//        this.departureDateTime = departureDateTime;
//        this.price = price;
//    }
//
//    public Ticket(int ticketNumber, Route route, MyUser buyer, Integer seatNumber, Date departureDateTime, double price) {
//        this.ticketNumber = ticketNumber;
//        this.route = route != null ? route : new Route();
//        this.user = buyer;
//        this.seatNumber = seatNumber;
//        this.departureDateTime = departureDateTime;
//        this.price = price;
//    }


    public Ticket(int ticketNumber, Route route, MyUser myUser, int seatNumber, Date departureDateTime, double price) {
        this.ticketNumber = ticketNumber;
        this.route = route != null ? route : new Route();
        this.user = myUser;
        this.seatNumber = seatNumber;
        this.departureDateTime = departureDateTime;
        this.price = price;
    }

    public Ticket() {

    }


    //public Ticket(int id, Route route, Long id1, int seatNumber, String departureDatetime, double price) {
    //}

    // Геттеры и сеттеры
    
    public int getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(int ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

//    public Buyer getBuyer() {
//        return buyer;
//    }
//
//    public void setBuyer(Buyer buyer) {
//        this.buyer = buyer;
//    }


    public MyUser getBuyer() {
        return user;
    }

    public void setBuyer(MyUser buyer) {
        this.user = buyer;
    }




    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Date getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(Date departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

//    public void setId(int id) {
//    }
//
//    public void setRouteId(int routeId) {
//    }
//
//    public void setBuyerId(int buyerId) {
//    }
}