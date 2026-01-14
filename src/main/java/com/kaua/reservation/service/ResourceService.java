package com.kaua.reservation.service;


import com.kaua.reservation.auth.AuthVerifyService;
import com.kaua.reservation.dto.request.ResourceRequest;
import com.kaua.reservation.dto.request.ResourceUpdateRequest;
import com.kaua.reservation.dto.response.ResourceResponse;
import com.kaua.reservation.entity.model.Resource;
import com.kaua.reservation.entity.model.User;
import com.kaua.reservation.entity.repository.ResourceRepository;
import com.kaua.reservation.exception.resource.OptimisticLockingException;
import com.kaua.reservation.exception.resource.ResourceAlreadyExistException;
import com.kaua.reservation.exception.resource.ResourceNotFoundException;
import com.kaua.reservation.exception.resource.ResourceVersionNotNullException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


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
            r.getVersion(),
            r.getId()
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


    public List<ResourceResponse> getResourceByName(String name){

        if(resourceRepository.findByName(name).isEmpty()){
            throw new ResourceNotFoundException();
        }

        return resourceRepository.findByName(name);


    }


    @Transactional
    public ResourceResponse updateResourceByName(ResourceUpdateRequest request, String name){

        User user = getAuthenticatedUser();

        Resource resource = resourceRepository.findByNameAndUser(name, user).orElseThrow(() ->
                new ResourceNotFoundException()
                );
        if(request.version() == null){
            throw new ResourceVersionNotNullException();
        }

        if(!resource.getVersion().equals(request.version())){
            throw new OptimisticLockingException();
        }

        if(request.name() != null && !request.name().isBlank()){
            resource.setName(request.name());
        }

        if(request.category() != null && !request.category().isBlank()){
            resource.setCategory(request.category());
        }

        if(request.capacity() != null){
            resource.setCapacity(request.capacity());
        }

        if(request.status() != null){
            resource.setStatus(request.status());
        }


        return toResponse(resourceRepository.save(resource));

    }

    @Transactional
    public void deleteResourceByName(String name){
        User user = getAuthenticatedUser();

        if(resourceRepository.findByNameAndUser(name, user).isEmpty()){
            throw new ResourceNotFoundException();
        }

        resourceRepository.deleteByName(name);
    }


}





































