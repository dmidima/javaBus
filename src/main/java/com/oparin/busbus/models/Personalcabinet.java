package com.oparin.busbus.models;

import java.util.ArrayList;
import java.util.List;

public class Personalcabinet {
    private int id;
    private List<String> ticketList;

    public Personalcabinet() {
        this.ticketList = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<String> ticketList) {
        this.ticketList = ticketList;
    }
}