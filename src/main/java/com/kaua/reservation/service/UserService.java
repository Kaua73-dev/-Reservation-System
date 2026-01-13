package com.kaua.reservation.service;


import com.kaua.reservation.auth.AuthVerifyService;
import com.kaua.reservation.config.TokenConfig;
import com.kaua.reservation.dto.request.LoginRequest;
import com.kaua.reservation.dto.request.RegisterRequest;
import com.kaua.reservation.dto.request.UserUpdateRequest;
import com.kaua.reservation.dto.response.LoginResponse;
import com.kaua.reservation.dto.response.RegisterResponse;
import com.kaua.reservation.dto.response.UserUpdateResponse;
import com.kaua.reservation.entity.model.User;
import com.kaua.reservation.entity.repository.UserRepository;
import com.kaua.reservation.exception.user.UserAlreadyExistException;
import com.kaua.reservation.exception.user.UserNoFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService extends AuthVerifyService{

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
        user.setEmail(request.email());
        user.setCpf(request.cpf());
        user.setPassword(passwordEncoder.encode(request.password()));

        User userSaved = userRepository.save(user);

        return new RegisterResponse(
                userSaved.getName(),
                userSaved.getEmail(),
                userSaved.getCpf()
        );

    }


    public LoginResponse login(LoginRequest request){
        if (userRepository.findByCpf(request.cpf()).isEmpty()){
            throw new UserNoFoundException();
        }

        UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(request.cpf(), request.password());
        Authentication authentication = authenticationManager.authenticate(userAndPass);

        User user = (User) authentication.getPrincipal();
        String token = tokenConfig.generateToken(user);

        return new LoginResponse(token);

    }


    @Transactional
    public void deleteUserByCpf(String cpf){
        User user = getAuthenticatedUser();

        if(!user.getCpf().equals(cpf)){
            throw new UserNoFoundException();
        }

        userRepository.deleteByCpf(cpf);

    }


    @Transactional
    public UserUpdateResponse updateUserByCpf(UserUpdateRequest request){
        User user = getAuthenticatedUser();



        if(request.name() != null){
            user.setName(request.name());
        }

        if(request.email() != null){
            user.setEmail(request.email());
        }

        if(request.password() != null && !request.password().isBlank()){
            user.setPassword(passwordEncoder.encode(request.password()));
        }


        userRepository.save(user);

        return new UserUpdateResponse(
                user.getName(),
                user.getCpf(),
                user.getEmail()
        );

    }





}
