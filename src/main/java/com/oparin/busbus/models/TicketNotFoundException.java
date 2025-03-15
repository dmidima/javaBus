package com.oparin.busbus.models;

// TicketNotFoundException.java
public class TicketNotFoundException extends RuntimeException {
    public TicketNotFoundException(String message) {
        super(message);
    }
}