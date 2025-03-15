package com.oparin.busbus.dao;
import com.oparin.busbus.models.Transport;

public interface TransportDao {
    void addTransport(Transport transport);
    Transport getTransportById(int id);
    void updateTransport(Transport transport);
    void deleteTransport(Transport transport);
}