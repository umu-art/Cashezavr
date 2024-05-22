package ru.kazenin.cherry.core.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kazenin.cherry.core.jpa.ClientJpa;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final ClientJpa clientJpa;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var result = clientJpa.findByUsername(username);

        if (result.isEmpty()) {
            throw new UsernameNotFoundException("Вас не существует");
        }

        return User.withUsername(username)
                .password(result.get().getPassword())
                .roles("client")
                .build();
    }
}
