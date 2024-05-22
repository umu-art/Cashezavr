package ru.kazenin.cherry.core.service;

import ru.kazenin.cherry.common.entity.ClientEntity;

public interface ContextHolder {
    ClientEntity getCurrentUser();
}
