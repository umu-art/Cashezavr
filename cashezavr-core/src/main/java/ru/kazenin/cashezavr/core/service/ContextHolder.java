package ru.kazenin.cashezavr.core.service;

import ru.kazenin.cashezavr.cashezavr.entity.ClientEntity;

public interface ContextHolder {
    ClientEntity getCurrentClient();
}
