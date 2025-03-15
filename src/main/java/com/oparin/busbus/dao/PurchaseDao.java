package com.oparin.busbus.dao;

import com.oparin.busbus.models.Purchase;

public interface PurchaseDao {
    void addPurchase(Purchase purchase);
    Purchase getPurchaseById(int id);
    void updatePurchase(Purchase purchase);
    void deletePurchase(Purchase purchase);
}