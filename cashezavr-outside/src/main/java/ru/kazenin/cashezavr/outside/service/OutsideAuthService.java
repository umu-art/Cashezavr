package ru.kazenin.cashezavr.outside.service;

public interface OutsideAuthService {

    void sendPhone(String phone);

    void sendCode(String code);

    String getSessionId();
}
