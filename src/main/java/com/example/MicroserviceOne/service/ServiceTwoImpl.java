package com.example.MicroserviceOne.service;

//import com.example.MicroserviceOne.entity.ServiceOneDto;
import com.example.MicroserviceOne.entity.ServiceOneEntity;
import com.example.MicroserviceOne.repo.ServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceTwoImpl implements ServiceTwo{
    @Autowired
    private ServiceRepo serviceRepo;

//    public ServiceOneDto convertEntityToDTO(ServiceOneEntity serviceOneEntity) {
//        ServiceOneDto serviceOneDto = new ServiceOneDto();
//        serviceOneDto.setId(serviceOneEntity.getId());
//        serviceOneDto.setServiceName(serviceOneEntity.getServiceName());
//        serviceOneDto.setRequestTime(serviceOneEntity.getRequestTime());
//        serviceOneDto.setResponseTime(serviceOneEntity.getResponseTime());
//        serviceOneDto.setStatusCode(serviceOneEntity.getStatusCode());
//        serviceOneDto.setTimeTaken(serviceOneEntity.getTimeTaken());
//        serviceOneDto.setRequestURI(serviceOneEntity.getRequestURI());
//        serviceOneDto.setRequestMethod(serviceOneEntity.getRequestMethod());
//        serviceOneDto.setRequestHeaderName(serviceOneEntity.getRequestHeaderName());
//        serviceOneDto.setContentType(serviceOneEntity.getContentType());
//        serviceOneDto.setRequestID(serviceOneEntity.getRequestID());
//        serviceOneDto.setHostName(serviceOneEntity.getHostName());
//        serviceOneDto.setResponse(serviceOneEntity.getResponse());
//        serviceOneDto.setErrorTrace(serviceOneEntity.getErrorTrace());
//
//        return serviceOneDto;
//    }
//
//    public ServiceOneEntity save(ServiceOneDto serviceOneDto) {
//        ServiceOneEntity serviceOneEntity = convertDTOToEntity(serviceOneDto);
//        return serviceRepo.save(serviceOneEntity);
//    }
//
//    private ServiceOneEntity convertDTOToEntity(ServiceOneDto serviceOneDto) {
//       ServiceOneEntity serviceOneEntity=new ServiceOneEntity();
//        serviceOneEntity.setId(serviceOneDto.getId());
//        serviceOneEntity.setServiceName(serviceOneDto.getServiceName());
//        serviceOneEntity.setRequestTime(serviceOneDto.getRequestTime());
//        serviceOneEntity.setResponseTime(serviceOneDto.getResponseTime());
//        serviceOneEntity.setStatusCode(serviceOneDto.getStatusCode());
//        serviceOneEntity.setTimeTaken(serviceOneDto.getTimeTaken());
//        serviceOneEntity.setRequestURI(serviceOneDto.getRequestURI());
//        serviceOneEntity.setRequestMethod(serviceOneDto.getRequestMethod());
//        serviceOneEntity.setRequestHeaderName(serviceOneDto.getRequestHeaderName());
//        serviceOneEntity.setContentType(serviceOneDto.getContentType());
//        serviceOneEntity.setRequestID(serviceOneDto.getRequestID());
//        serviceOneEntity.setHostName(serviceOneDto.getHostName());
//        serviceOneEntity.setResponse(serviceOneDto.getResponse());
//        serviceOneEntity.setErrorTrace(serviceOneDto.getErrorTrace());
//
//        return serviceOneEntity;
//    }

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
