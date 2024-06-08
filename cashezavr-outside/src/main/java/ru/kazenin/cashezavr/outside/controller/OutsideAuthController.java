package ru.kazenin.cashezavr.outside.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import ru.kazenin.api.OutsideAuthApi;
import ru.kazenin.cashezavr.outside.service.OutsideAuthService;

@Controller
@RequiredArgsConstructor
public class OutsideAuthController implements OutsideAuthApi {

    private final OutsideAuthService outsideAuthService;

    @Override
    public ResponseEntity<Void> cashezavrOutsideSendMessagePost(String body) {
        outsideAuthService.sendPhone(body);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> cashezavrOutsideCodePost(String body) {
        outsideAuthService.sendCode(body);
        return ResponseEntity.ok().build();
    }
}
