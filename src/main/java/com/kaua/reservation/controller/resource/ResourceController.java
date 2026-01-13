package com.kaua.reservation.controller.resource;



import com.kaua.reservation.dto.request.ResourceRequest;
import com.kaua.reservation.dto.response.ResourceResponse;
import com.kaua.reservation.service.ResourceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
    public ResponseEntity<ResourceResponse> create(@Validated @RequestBody ResourceRequest request){

        ResourceResponse response = resourceService.createResource(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);

    }


}
