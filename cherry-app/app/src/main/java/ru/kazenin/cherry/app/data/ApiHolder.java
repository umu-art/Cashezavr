package ru.kazenin.cherry.app.data;

import ru.kazenin.api.BillApi;
import ru.kazenin.api.ClientApi;
import ru.kazenin.api.ReceiptApi;
import ru.kazenin.api.RegisterApi;

public class ApiHolder {

    public static final RegisterApi registerApi = new RegisterApi();
    public static final ReceiptApi receiptApi = new ReceiptApi();
    public static final ClientApi clientApi = new ClientApi();
    public static final BillApi billApi = new BillApi();

    private static final String basePath = "http://192.168.120.27:3001";
    private static final String AUTHORIZATION = "Authorization";

    public static void init() {
        registerApi.setBasePath(basePath);
        receiptApi.setBasePath(basePath);
        clientApi.setBasePath(basePath);
        billApi.setBasePath(basePath);
    }

    public static void setAuth(String auth) {
        String authStr = "Basic " + auth;

        registerApi.addHeader(AUTHORIZATION, authStr);
        receiptApi.addHeader(AUTHORIZATION, authStr);
        clientApi.addHeader(AUTHORIZATION, authStr);
        billApi.addHeader(AUTHORIZATION, authStr);
    }
}
