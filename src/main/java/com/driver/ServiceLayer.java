package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceLayer {

//    @Autowired
//    private RepositoryLayer repositoryLayerObj;

    RepositoryLayer repositoryLayerObj = new RepositoryLayer();

    public void addOrder(Order order){

        repositoryLayerObj.addOrder(order);
    }

    public void addPartner(String partnerId){
        repositoryLayerObj.addPartner(partnerId);
    }

    public void addOrderPartnerPair(String orderId, String partnerId){
        repositoryLayerObj.addOrderPartnerPair(orderId, partnerId);
    }

    public Order getOrderById(String orderId){
        return repositoryLayerObj.getOrderById(orderId);
    }

    public DeliveryPartner getPartnerById(String partnerId){
        return repositoryLayerObj.getPartnerById(partnerId);
    }

    public Integer getOrderCountByPartnerId(String partnerId){
        return repositoryLayerObj.getOrderCountByPartnerId(partnerId);
    }
    public List<String> getOrdersByPartnerId(String partnerId){
        return repositoryLayerObj.getOrdersByPartnerId(partnerId);
    }

    public List<String> getAllOrders(){
        return repositoryLayerObj.getAllOrders();
    }

    public Integer getCountOfUnassignedOrders(){
        return repositoryLayerObj.getCountOfUnassignedOrders();
    }

    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId){
        return repositoryLayerObj.getOrdersLeftAfterGivenTimeByPartnerId(time, partnerId);
    }

    public String getLastDeliveryTimeByPartnerId(String partnerId){
        return repositoryLayerObj.getLastDeliveryTimeByPartnerId(partnerId);
    }

    public void deletePartnerById(String partnerId){
        repositoryLayerObj.deletePartnerById(partnerId);
    }

    public void deleteOrderById(String orderId){
        repositoryLayerObj.deleteOrderById(orderId);
    }
}
