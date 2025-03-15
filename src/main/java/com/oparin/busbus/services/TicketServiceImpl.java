package com.oparin.busbus.services;

import com.oparin.busbus.models.Ticket;
import com.oparin.busbus.models.TicketNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.oparin.busbus.dao.TicketDao;
@Service
public class TicketServiceImpl implements TicketService {


    @Autowired
    private TicketDao ticketDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public void addTicket(Ticket ticket) {
        ticketDao.addTicket(ticket);
    }

//    @Transactional(propagation = Propagation.REQUIRES_NEW)
//    public void deleteTicket(int ticketNumber) {
//
//    }

//    @Transactional(propagation = Propagation.REQUIRES_NEW)
//    public void deleteTicket(int ticketNumber) {
//        ticketDao.deleteTicket(ticketNumber);
//    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteTicket(int ticketNumber) {
        if (!ticketDao.ticketExists(ticketNumber)) {
            throw new TicketNotFoundException("Ticket with id " + ticketNumber + " does not exist");
        }
        ticketDao.deleteTicket(ticketNumber);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void transactionalMethod() {
        Ticket ticket = new Ticket();
        addTicket(ticket);
        deleteTicket(1001);
    }
}
