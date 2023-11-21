package com.example.MicroserviceOne.service;

import com.example.MicroserviceOne.entity.ServiceOneEntity;
import com.example.MicroserviceOne.repo.ServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceTwoImpl implements ServiceTwo{
    @Autowired
    private ServiceRepo serviceRepo;

    @Override
    public ServiceOneEntity saveEntity(ServiceOneEntity serviceOneEntity) {
        return serviceRepo.save(serviceOneEntity);
    }


//    @Override
//    public ServiceOneEntity save(ServiceOneEntity serviceOneEntity) {
//        return serviceRepo.save(serviceOneEntity);
//    }
//    @Override
//    public ServiceOneEntity save(ServiceOneEntity serviceOneEntity) {
//        return serviceRepo.save(serviceOneEntity);
//    }
}
