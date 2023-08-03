package com.driver;

import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class RepositoryLayer {

    HashMap<String, Order> orderDb = new HashMap<>();
    HashMap<String, DeliveryPartner> deliveryPartnerDb = new HashMap<>();

    public void addOrder(Order order){
        String orderId = order.getId();
        orderDb.put(orderId, order);
    }

    public void addPartner(String partnerId){
        DeliveryPartner dp = new DeliveryPartner(partnerId);
        deliveryPartnerDb.put(partnerId, dp);
    }
}
