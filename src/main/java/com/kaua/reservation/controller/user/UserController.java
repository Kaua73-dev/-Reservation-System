package com.kaua.reservation.controller.user;


import com.kaua.reservation.dto.request.LoginRequest;
import com.kaua.reservation.dto.request.RegisterRequest;
import com.kaua.reservation.dto.response.LoginResponse;
import com.kaua.reservation.dto.response.RegisterResponse;
import com.kaua.reservation.entity.model.User;
import com.kaua.reservation.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Validated @RequestBody RegisterRequest request){

        RegisterResponse response = userService.register(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);

    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Validated @RequestBody LoginRequest request){
        return ResponseEntity.ok(userService.login(request));
    }

    @DeleteMapping("/user/{cpf}")
    public void deleteUserByCpf(@PathVariable String cpf){
        userService.deleteUserByCpf(cpf);
    }

}
