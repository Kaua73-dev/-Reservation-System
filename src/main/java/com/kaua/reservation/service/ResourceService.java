package com.kaua.reservation.service;


import com.kaua.reservation.auth.AuthVerifyService;
import com.kaua.reservation.dto.request.ResourceRequest;
import com.kaua.reservation.dto.response.ResourceResponse;
import com.kaua.reservation.entity.model.Resource;
import com.kaua.reservation.entity.model.User;
import com.kaua.reservation.entity.repository.ResourceRepository;
import com.kaua.reservation.exception.resource.ResourceAlreadyExistException;
import com.kaua.reservation.exception.resource.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResourceService extends AuthVerifyService {

    private final ResourceRepository resourceRepository;

    public ResourceService(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    private ResourceResponse toResponse(Resource r){
        return new ResourceResponse(
            r.getName(),
            r.getCategory(),
            r.getCapacity(),
            r.getStatus(),
            r.getVersion()
        );
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


       return toResponse(resourceRepository.save(resource));




    }

    public List<ResourceResponse> findAllResource(){
        return resourceRepository.findAll()
                .stream()
                .map(this::toResponse).toList();
    }

    public Optional<ResourceResponse> getResourceByName(String name){

        if(resourceRepository.findByName(name).isEmpty()){
            throw new ResourceNotFoundException();
        }

        return resourceRepository.findByName(name);


    }


}





































