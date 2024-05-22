package ru.kazenin.cherry.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.kazenin.cherry.common.entity.ClientEntity;
import ru.kazenin.cherry.core.jpa.ClientJpa;
import ru.kazenin.cherry.core.service.ContextHolder;

@Service
@RequiredArgsConstructor
public class ContextHolderImpl implements ContextHolder {

    private final ClientJpa clientJpa;

    @Override
    public ClientEntity getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        return clientJpa.findByUsername(currentUsername).get();
    }
}
