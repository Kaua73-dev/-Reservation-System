package com.kaua.reservation.service;


import com.kaua.reservation.auth.AuthVerifyService;
import com.kaua.reservation.dto.request.ResourceRequest;
import com.kaua.reservation.dto.response.ResourceResponse;
import com.kaua.reservation.entity.model.User;
import com.kaua.reservation.entity.repository.ResourceRepository;
import org.springframework.stereotype.Service;

@Service
public class ResourceService extends AuthVerifyService {

    private final ResourceRepository resourceRepository;


    public ResourceService(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }


    public ResourceResponse createResource(ResourceRequest request){
        User user = getAuthenticatedUser();






    }





}
