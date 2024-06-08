package ru.kazenin.cashezavr.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BillStatus {
    WAITING("Ожидание");

    private final String value;
}
