package com.oparin.busbus.models;

import java.util.Date;

public class Purchase {
    private int id;
    private int ticketId;
    private Date purchaseDate;
    private boolean purchaseStatus;
    private boolean returnStatus;
    private int buyerId;

    // Конструктор
    public Purchase(int ticketId, Date purchaseDate, boolean purchaseStatus, boolean returnStatus, int buyerId) {
        this.ticketId = ticketId;
        this.purchaseDate = purchaseDate;
        this.purchaseStatus = purchaseStatus;
        this.returnStatus = returnStatus;
        this.buyerId = buyerId;
    }

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public boolean isPurchaseStatus() {
        return purchaseStatus;
    }

    public void setPurchaseStatus(boolean purchaseStatus) {
        this.purchaseStatus = purchaseStatus;
    }

    public boolean isReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(boolean returnStatus) {
        this.returnStatus = returnStatus;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }
}