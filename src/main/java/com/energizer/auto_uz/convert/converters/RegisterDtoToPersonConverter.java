package com.energizer.auto_uz.convert.converters;

import com.energizer.auto_uz.dto.reques.RegisterRequest;
import com.energizer.auto_uz.models.users.Person;
import com.energizer.auto_uz.models.users.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegisterDtoToPersonConverter implements Converter<RegisterRequest, Person> {
    @Override
    public Person convert(RegisterRequest source) {
        return new Person(
                source.username(),
                source.email(),
                passwordEncoder.encode(source.password()),
                Role.ROLE_USER
        );
    }
    private final PasswordEncoder passwordEncoder;
}
