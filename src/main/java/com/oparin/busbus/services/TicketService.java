package com.oparin.busbus.services;

import com.oparin.busbus.models.Ticket;

public interface TicketService {

    void addTicket(Ticket ticket);

    void deleteTicket(int ticketNumber);

    void transactionalMethod();
}


