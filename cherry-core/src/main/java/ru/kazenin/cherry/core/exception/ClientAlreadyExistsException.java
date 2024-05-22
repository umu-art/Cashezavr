package ru.kazenin.cherry.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ClientAlreadyExistsException extends RuntimeException {
    public ClientAlreadyExistsException(String username) {
        super("Клиент с именем " + username + " уже существует");
    }
}
