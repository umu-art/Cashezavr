package ru.kazenin.cherry.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import ru.kazenin.api.ClientApi;
import ru.kazenin.cherry.core.service.ClientService;
import ru.kazenin.model.ClientDto;

@Controller
@RequiredArgsConstructor
public class ClientController implements ClientApi {

    private final ClientService clientService;

    @Override
    public ResponseEntity<ClientDto> getMe() {
        return ResponseEntity.ok(clientService.getMe());
    }
}
