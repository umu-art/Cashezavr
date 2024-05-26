package ru.kazenin.cherry.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kazenin.cherry.core.mapper.ClientMapper;
import ru.kazenin.cherry.core.service.ClientService;
import ru.kazenin.cherry.core.service.ContextHolder;
import ru.kazenin.model.ClientDto;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ContextHolder contextHolder;
    private final ClientMapper clientMapper;

    @Override
    public ClientDto getMe() {
        return clientMapper.toDto(contextHolder.getCurrentClient());
    }
}
