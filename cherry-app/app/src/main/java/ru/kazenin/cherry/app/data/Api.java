package ru.kazenin.cherry.app.data;

import android.os.StrictMode;
import ru.kazenin.api.RegisterApi;

public class Api {

    public static final RegisterApi registerApi = new RegisterApi();
    private static String basePath = "http://192.168.120.27:3001";

    public static void init() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        registerApi.setBasePath(basePath);
    }

    public static void setAuth(String auth) {
        Api.registerApi.addHeader("Authorization", "Basic " + auth);
    }
}
