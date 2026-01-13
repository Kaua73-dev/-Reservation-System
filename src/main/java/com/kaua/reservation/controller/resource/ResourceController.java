package com.kaua.reservation.controller.resource;



import com.kaua.reservation.dto.request.ResourceRequest;
import com.kaua.reservation.dto.response.ResourceResponse;
import com.kaua.reservation.entity.model.Resource;
import com.kaua.reservation.service.ResourceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class ResourceController {

   private final ResourceService resourceService;

    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }




    @PostMapping("/resource")
    public ResponseEntity<ResourceResponse> create(@Validated @RequestBody ResourceRequest request){

        ResourceResponse response = resourceService.createResource(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);

    }

    @GetMapping("/resource")
    public List<ResourceResponse> findAllResource(){
        return resourceService.findAllResource();
    }


    @GetMapping("resource/{name}")
    public Optional<ResourceResponse> findResourceByName(@PathVariable String name){
        return resourceService.getResourceByName(name);
    }

}
