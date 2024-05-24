package ru.kazenin.cherry.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ReceiptAlreadyExistsException extends RuntimeException {
    public ReceiptAlreadyExistsException() {
        super("Такой чек уже загружали");
    }
}
