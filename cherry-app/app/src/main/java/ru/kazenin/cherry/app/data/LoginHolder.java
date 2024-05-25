package ru.kazenin.cherry.app.data;

import android.util.Log;
import lombok.var;
import ru.kazenin.cherry.app.data.model.LoggedInUser;
import ru.kazenin.model.RegisterDto;

import java.util.Base64;

public class LoginHolder {

    public static LoggedInUser loggedInUser;

    public static boolean loginAttempt(String username,
                                       String password) {
        try {
            var auth = Base64.getEncoder().encodeToString((username + ":" + password).getBytes());

            Api.setAuth(auth);
            Api.registerApi.tryAuth();
            loggedInUser = new LoggedInUser(username, auth);

            return true;
        } catch (Exception e) {
            Log.e("login", "loginAttempt failed:", e);
            return false;
        }
    }

    public static boolean registerAttempt(String username,
                                          String password,
                                          String email,
                                          String phone) {
        try {
            var auth = Base64.getEncoder().encodeToString((username + ":" + password).getBytes());

            var registerDto = new RegisterDto();
            registerDto.setUsername(username);
            registerDto.setPassword(password);
            registerDto.setEmail(email);
            registerDto.setPhone(phone);

            Api.registerApi.register(registerDto);
            Api.setAuth(auth);
            loggedInUser = new LoggedInUser(username, auth);

            return true;
        } catch (Exception e) {
            Log.e("register", "registerAttempt failed:", e);
            return false;
        }
    }
}