package ru.kazenin.cherry.outside.service;

public interface OutsideAuthService {

    void sendPhone(String phone);

    void sendCode(String code);

    String getSessionId();
}
