package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Repository
public class RepositoryLayer {

    HashMap<String, Order> orderDb = new HashMap<>();
    HashMap<String, DeliveryPartner> deliveryPartnerDb = new HashMap<>();
    HashMap<String, List<Order>> addOrderPartnerDb = new HashMap<>();

    HashSet<String> ordersNotAssignedDb = new HashSet<>();

    public void addOrder(Order order){
        String orderId = order.getId();
        orderDb.put(orderId, order);
    }

    public void addPartner(String partnerId){
        DeliveryPartner dp = new DeliveryPartner(partnerId);
        deliveryPartnerDb.put(partnerId, dp);
    }

    public void addOrderPartnerPair(String orderId, String partnerId){
        List<Order> list = addOrderPartnerDb.getOrDefault(partnerId, new ArrayList<>());
        Order order = orderDb.get(orderId);
        list.add(order);
        addOrderPartnerDb.put(partnerId, list);
        DeliveryPartner partner = deliveryPartnerDb.get(partnerId);
        Integer noOfOrders = partner.getNumberOfOrders();
        noOfOrders++;
        partner.setNumberOfOrders(noOfOrders);
        ordersNotAssignedDb.remove(orderId);
    }

    public Order getOrderById(String orderId){
        return orderDb.get(orderId);
    }

    public DeliveryPartner getPartnerById(String partnerId){
        return deliveryPartnerDb.get(partnerId);
    }

    public Integer getOrderCountByPartnerId(String partnerId){
        List<Order> list = addOrderPartnerDb.get(partnerId);
        return list.size();
    }

    public List<String> getOrdersByPartnerId(String partnerId){
        List<Order> list = addOrderPartnerDb.get(partnerId);
        List<String> strList = new ArrayList<>();
        for(Order order : list){
            strList.add(order.getId());
        }
        return strList;
    }

    public List<String> getAllOrders(){
        List<String> strList = new ArrayList<>();
        for(String orderId : orderDb.keySet()){
            strList.add(orderId);
        }
        return strList;
    }

    public Integer getCountOfUnassignedOrders(){
        return ordersNotAssignedDb.size();
    }

    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId){
        String arr[] = time.split(":");
        Integer hr = Integer.parseInt(arr[0]);
        Integer min = Integer.parseInt(arr[1]);
        Integer totalTime = hr*60+min;

        List<Order> list = addOrderPartnerDb.getOrDefault(partnerId, new ArrayList<>());
        if(list.size() == 0) return 0;

        int count = 0;
        for(Order order : list){
            if(order.getDeliveryTime() > totalTime) count++;
        }

        return count;
    }

    public String getLastDeliveryTimeByPartnerId(String partnerId){
        List<Order> list = addOrderPartnerDb.getOrDefault(partnerId, new ArrayList<>());
        if(list.size() == 0) return "00:00";
        Integer max = 0;
        for(Order order : list){
            max = Math.max(order.getDeliveryTime(), max);
        }

        Integer hr = max/60;
        Integer min = max%60;
        String str = "";

        if(hr < 10) str += "0"+hr+":";
        else str += hr+":";

        if(min < 10) str += "0"+min;
        else str += min;

        return str;
    }

    public void deletePartnerById(String partnerId){
        List<Order> list = addOrderPartnerDb.get(partnerId);
        if(list.size() > 0) {
            for (Order order : list) {
                ordersNotAssignedDb.add(order.getId());
            }
        }
        addOrderPartnerDb.remove(partnerId);
        deliveryPartnerDb.remove(partnerId);
    }

    public void deleteOrderById(String orderId){
        if(ordersNotAssignedDb.contains(orderId)) ordersNotAssignedDb.add(orderId);

        Order order = orderDb.get(orderId);
        for(String partner : addOrderPartnerDb.keySet()){
            List<Order> list = addOrderPartnerDb.get(partner);
            list.remove(order);
        }

        if(orderDb.containsKey(orderId)) orderDb.remove(orderId);
    }
}
