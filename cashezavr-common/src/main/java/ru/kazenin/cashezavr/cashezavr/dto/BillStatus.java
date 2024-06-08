package ru.kazenin.cashezavr.cashezavr.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BillStatus {
    WAITING("Ожидание");

    private final String value;
}
