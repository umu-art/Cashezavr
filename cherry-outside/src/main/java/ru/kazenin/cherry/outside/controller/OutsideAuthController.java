package ru.kazenin.cherry.outside.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import ru.kazenin.api.OutsideAuthApi;
import ru.kazenin.cherry.outside.service.OutsideAuthService;

@Controller
@RequiredArgsConstructor
public class OutsideAuthController implements OutsideAuthApi {

    private final OutsideAuthService outsideAuthService;

    @Override
    public ResponseEntity<Void> cherryOutsideSendMessagePost(String body) {
        outsideAuthService.sendPhone(body);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> cherryOutsideCodePost(String body) {
        outsideAuthService.sendCode(body);
        return ResponseEntity.ok().build();
    }
}
