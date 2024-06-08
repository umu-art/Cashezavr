package ru.kazenin.cashezavr.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import ru.kazenin.api.RegisterApi;
import ru.kazenin.cashezavr.core.service.RegisterService;
import ru.kazenin.model.RegisterDto;

@Controller
@RequiredArgsConstructor
public class RegisterController implements RegisterApi {

    private final RegisterService registerService;

    @Override
    public ResponseEntity<Void> register(RegisterDto registerDto) {
        registerService.register(registerDto);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> tryAuth() {
        return ResponseEntity.ok().build();
    }
}
