package com.example.MicroserviceOne.repo;

import com.example.MicroserviceOne.entity.ServiceOneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ServiceRepo extends JpaRepository<ServiceOneEntity,Long> {


}
