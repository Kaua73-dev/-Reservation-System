package com.kaua.reservation.controller.resource;


import com.kaua.reservation.dto.request.RegisterRequest;
import com.kaua.reservation.dto.response.ResourceResponse;
import com.kaua.reservation.entity.repository.ResourceRepository;
import com.kaua.reservation.service.ResourceService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class ResourceController {

   private final ResourceService resourceService;

    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }


    @PostMapping("/resource")
    public ResourceResponse createResource(@RequestBody RegisterRequest request){
        return
    }


}
