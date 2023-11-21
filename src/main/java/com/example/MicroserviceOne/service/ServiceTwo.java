package com.example.MicroserviceOne.service;

import com.example.MicroserviceOne.entity.ServiceOneEntity;

public interface ServiceTwo {
    public ServiceOneEntity saveEntity(ServiceOneEntity serviceOneEntity);

}
