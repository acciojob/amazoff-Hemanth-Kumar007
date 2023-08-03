package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceLayer {

    @Autowired
    private RepositoryLayer repositoryLayerObj;

    public void addOrder(Order order){

        repositoryLayerObj.addOrder(order);
    }

    public void addPartner(String partnerId){
        repositoryLayerObj.addPartner(partnerId);
    }
}
