package com.kaua.reservation.config;


import com.kaua.reservation.entity.model.User;
import com.kaua.reservation.entity.model.UserRepository;
import com.kaua.reservation.exception.user.UserNoFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class SecurityFilterConfig extends OncePerRequestFilter {

    @Autowired
    TokenConfig tokenConfig;

    @Autowired
    UserRepository userRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);

        if(token != null){
            var cpf = tokenConfig.validatetoken(token);
            UserDetails user = userRepository.findByCpf(cpf).orElseThrow(() ->
                    new UserNoFoundException()
                    );

        var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);

    }

    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authrozation");
        if(authHeader == null) return null;

        return authHeader.replace("Bearer ", "");
    }

}
