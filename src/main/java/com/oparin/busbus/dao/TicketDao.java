package com.oparin.busbus.dao;

import com.oparin.busbus.models.Route;
import com.oparin.busbus.models.Ticket;

public interface TicketDao {

    void addTicket(Ticket ticket);

    boolean deleteTicket(int ticketNumber);

//    Ticket[] viewTickets();


    public default boolean ticketExists(int ticketNumber) {
        // Здесь ваша логика проверки наличия билета по его номеру
        // Вернуть true, если билет существует, и false в противном случае
        return false;
    }

//    public void deleteTicket(int ticketNumber) {
//        // Здесь ваша логика удаления билета по его номеру
//    }


    Ticket[] viewTickets(int userId);

//    Route getTicketById(int id);

}




//public interface TicketDao {
//
//    void addTicket(Ticket ticket);
//
//    boolean deleteTicket(int ticketNumber);
//
////    Ticket[] viewTickets();
//
//    Ticket[] viewTickets(int userId);
//
////    Route getTicketById(int id);
//
//}