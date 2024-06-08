package ru.kazenin.cashezavr.core.service;

import ru.kazenin.cashezavr.common.entity.ClientEntity;

public interface ContextHolder {
    ClientEntity getCurrentClient();
}
