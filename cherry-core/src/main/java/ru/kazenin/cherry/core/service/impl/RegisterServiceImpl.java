package ru.kazenin.cherry.core.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kazenin.cherry.core.exception.UserAlreadyExistsException;
import ru.kazenin.cherry.core.jpa.ClientJpa;
import ru.kazenin.cherry.core.mapper.ClientMapper;
import ru.kazenin.cherry.core.service.RegisterService;
import ru.kazenin.model.RegisterDto;

@Service
@Slf4j
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    private final ClientJpa clientJpa;
    private final ClientMapper clientMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(RegisterDto registerDto) {
        var user = clientJpa.findByUsername(registerDto.getUsername());

        if (user.isPresent()) {
            throw new UserAlreadyExistsException(registerDto.getUsername());
        }

        var entity = clientMapper.toEntity(registerDto);
        entity.setPassword(passwordEncoder.encode(entity.getPassword())); // TODO: Под выпил

        clientJpa.saveAndFlush(entity);

        log.info("Новый пользователь {}", registerDto.getUsername());
    }
}
