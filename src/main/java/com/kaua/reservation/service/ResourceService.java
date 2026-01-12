package com.kaua.reservation.service;


import com.kaua.reservation.entity.repository.ResourceRepository;
import org.springframework.stereotype.Service;

@Service
public class ResourceService {

    private final ResourceRepository resourceRepository;


    public ResourceService(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }








}
