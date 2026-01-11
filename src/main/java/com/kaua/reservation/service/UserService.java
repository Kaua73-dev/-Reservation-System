package com.kaua.reservation.service;


import com.kaua.reservation.config.TokenConfig;
import com.kaua.reservation.dto.request.LoginRequest;
import com.kaua.reservation.dto.request.RegisterRequest;
import com.kaua.reservation.dto.response.Loginresponse;
import com.kaua.reservation.dto.response.RegisterResponse;
import com.kaua.reservation.entity.model.User;
import com.kaua.reservation.entity.model.UserRepository;
import com.kaua.reservation.exception.user.UserAlreadyExistException;
import com.kaua.reservation.exception.user.UserNoFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenConfig tokenConfig;

    public UserService(UserRepository repository, BCryptPasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenConfig tokenConfig) {
        this.userRepository = repository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenConfig = tokenConfig;
    }



    public RegisterResponse register(RegisterRequest request){
        if(userRepository.findByCpf(request.cpf()).isPresent()){
            throw  new UserAlreadyExistException();
        }

        User user = new User();
        user.setName(request.name());
        user.setCpf(request.cpf());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));

        User userSaved = userRepository.save(user);

        return new RegisterResponse(
                userSaved.getName(),
                userSaved.getCpf(),
                userSaved.getEmail()
        );

    }


    public Loginresponse login(LoginRequest request){
        if (userRepository.findByCpf(request.cpf()).isEmpty()){
            throw new UserNoFoundException();
        }

        UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(request.cpf(), request.password());
        Authentication authentication = authenticationManager.authenticate(userAndPass);

        User user = (User) authentication.getPrincipal();
        String token = tokenConfig.generateToken(user);

        return new Loginresponse(token);




    }


}
