package com.energizer.auto_uz.utils;

import com.energizer.auto_uz.dto.reques.JwtRequest;
import com.energizer.auto_uz.dto.response.JwtResponse;
import com.energizer.auto_uz.models.users.Person;
import com.energizer.auto_uz.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginUtil {
    public JwtResponse buildToken(JwtRequest request) {
        // аутентификация на основе PersonDetailsService и PasswordEncoder
        authManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));
        Person person = personService.findByEmail(request.email()).orElseThrow();
        String token = jwtTokenUtil.generateToken(person);
        return new JwtResponse(token);
    }
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authManager;
    private final PersonService personService;
}
