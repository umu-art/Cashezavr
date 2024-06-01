package ru.kazenin.cherry.app.data;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import lombok.var;
import ru.kazenin.cherry.app.data.model.LoggedInUser;
import ru.kazenin.model.RegisterDto;

import java.util.Base64;

import static java.util.Objects.nonNull;

public class LoginHolder {

    public static LoggedInUser loggedInUser;
    public static SharedPreferences pm;
    public static SharedPreferences.Editor editor;

    public static void tryLoadFromMem(Activity activity) {
        pm = PreferenceManager.getDefaultSharedPreferences(activity);
        editor = pm.edit();

        loggedInUser = new LoggedInUser(null, null);
        loggedInUser.setUsername(pm.getString("username", null));
        loggedInUser.setAuth(pm.getString("auth", null));

        if (nonNull(loggedInUser.getAuth())) {
            ApiHolder.setAuth(loggedInUser.getAuth());
        }
    }


    public static boolean loginAttempt(String username,
                                       String password) {
        try {
            var auth = Base64.getEncoder().encodeToString((username + ":" + password).getBytes());

            ApiHolder.setAuth(auth);
            ApiHolder.registerApi.tryAuth();
            loggedInUser = new LoggedInUser(username, auth);

            editor.putString("username", username);
            editor.putString("auth", auth);
            editor.commit();

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

            ApiHolder.registerApi.register(registerDto);
            ApiHolder.setAuth(auth);
            loggedInUser = new LoggedInUser(username, auth);

            editor.putString("username", username);
            editor.putString("auth", auth);
            editor.commit();

            return true;
        } catch (Exception e) {
            Log.e("register", "registerAttempt failed:", e);
            return false;
        }
    }
}