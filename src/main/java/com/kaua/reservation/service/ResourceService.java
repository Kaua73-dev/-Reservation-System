package com.kaua.reservation.service;


import com.kaua.reservation.auth.AuthVerifyService;
import com.kaua.reservation.dto.request.ResourceRequest;
import com.kaua.reservation.dto.response.ResourceResponse;
import com.kaua.reservation.entity.model.Resource;
import com.kaua.reservation.entity.model.User;
import com.kaua.reservation.entity.repository.ResourceRepository;
import com.kaua.reservation.exception.resource.ResourceAlreadyExistException;
import org.springframework.stereotype.Service;

@Service
public class ResourceService extends AuthVerifyService {

    private final ResourceRepository resourceRepository;


    public ResourceService(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }


    public ResourceResponse createResource(ResourceRequest request){
        User user = getAuthenticatedUser();

        if(resourceRepository.findByNameAndUser(request.name(), user).isPresent()){
            throw new ResourceAlreadyExistException();
        }


        Resource resource = new Resource();
        resource.setName(request.name());
        resource.setCategory(request.category());
        resource.setCapacity(request.capacity());
        resource.setStatus(request.status());
        resource.setUser(user);


        resourceRepository.save(resource);


        return new ResourceResponse(
            resource.getName(),
            request.category(),
            request.capacity(),
            request.status()
        );


    }





}
