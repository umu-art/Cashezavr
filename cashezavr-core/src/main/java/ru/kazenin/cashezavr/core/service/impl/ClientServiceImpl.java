package ru.kazenin.cashezavr.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kazenin.cashezavr.core.mapper.ClientMapper;
import ru.kazenin.cashezavr.core.service.ClientService;
import ru.kazenin.cashezavr.core.service.ContextHolder;
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
