package com.energizer.auto_uz.services;

import com.energizer.auto_uz.models.users.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonDetailsService implements UserDetailsService {

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Person person = personService.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(
                "Пользователь с почтой " + email + "не найден")
        );
        return new User(
                person.getEmail(),
                person.getPassword(),
                List.of(new SimpleGrantedAuthority(person.getRole().name()))
        );
    }

    private final PersonService personService;
}
