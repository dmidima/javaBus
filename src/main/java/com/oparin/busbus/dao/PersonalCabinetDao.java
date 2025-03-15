package com.oparin.busbus.dao;

import com.oparin.busbus.models.Personalcabinet;

public interface PersonalcabinetDao {
    void viewTickets();
    String returnTicket(String ticketNumber);
}