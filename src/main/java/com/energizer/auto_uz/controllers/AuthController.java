package com.energizer.auto_uz.controllers;

import com.energizer.auto_uz.dto.reques.JwtRequest;
import com.energizer.auto_uz.dto.response.JwtResponse;
import com.energizer.auto_uz.dto.reques.RegisterRequest;
import com.energizer.auto_uz.dto.errors.DataErrors;
import com.energizer.auto_uz.exceptions.UserNotCreatedException;
import com.energizer.auto_uz.services.PersonService;
import com.energizer.auto_uz.utils.ErrorsUtil;
import com.energizer.auto_uz.utils.LoginUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    @PostMapping("/login")
    public JwtResponse createToken(@RequestBody JwtRequest request) {
        return loginUtil.buildToken(request);
    }
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@RequestBody @Valid RegisterRequest registerRequest, BindingResult errors) {
        if(errors.hasErrors()) {
            throw new UserNotCreatedException(errorsUtil.getDataErrors(errors));
        }
        personService.register(registerRequest);
    }
    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    private DataErrors badCredentials(BadCredentialsException e) {
        return errorsUtil.getDateErrors("Неверные email или пароль");
    }
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private DataErrors badCredentials(UserNotCreatedException e) {
        return e.getErrors();
    }

    private final PersonService personService;
    private final ErrorsUtil errorsUtil;
    private final LoginUtil loginUtil;
}
