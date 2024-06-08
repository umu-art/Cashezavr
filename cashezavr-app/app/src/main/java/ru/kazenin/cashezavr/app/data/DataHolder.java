package ru.kazenin.cashezavr.app.data;

import android.util.Log;
import ru.kazenin.ApiException;
import ru.kazenin.model.BillDto;
import ru.kazenin.model.ClientDto;
import ru.kazenin.model.ReceiptDto;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static java.util.Objects.isNull;

public class DataHolder {

    public static final int updatePeriod = 5000;

    public static ReceiptDto lastReceipt = new ReceiptDto();

    public static List<BillDto> bills;
    public static List<ReceiptDto> receiptDtos;
    public static ClientDto clientDto;

    private static final Timer timer = new Timer();
    private static boolean runned = false;

    public static void init() {
        if (runned) {
            return;
        }
        runned = true;
        Log.i("DataHolder", "init: started");

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (isNull(LoginHolder.loggedInUser)) {
                    return;
                }

                try {
                    bills = ApiHolder.billApi.getAllBills();
                    receiptDtos = ApiHolder.receiptApi.getAllReceipts();
                    clientDto = ApiHolder.clientApi.getMe();
                    Log.d("loaded data", bills.toString() + "\n" + receiptDtos.toString() + "\n" + clientDto.toString());
                } catch (ApiException e) {
                    Log.e("data load", "error: ", e);
                }
            }
        }, 0, updatePeriod);
    }

    public static void fillBillsIfNull() {
        if (isNull(bills)) {
            try {
                bills = ApiHolder.billApi.getAllBills();
            } catch (ApiException e) {
                e.printStackTrace();
            }
        }
    }

    public static void recallBills() {
        try {
            bills = ApiHolder.billApi.getAllBills();
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    public static void fillReceiptDtosIfNull() {
        if (isNull(receiptDtos)) {
            try {
                receiptDtos = ApiHolder.receiptApi.getAllReceipts();
            } catch (ApiException e) {
                e.printStackTrace();
            }
        }
    }

    public static void fillClientDtoIfNull() {
        if (isNull(clientDto)) {
            try {
                clientDto = ApiHolder.clientApi.getMe();
            } catch (ApiException e) {
                e.printStackTrace();
            }
        }
    }

    public static void recallClient() {
        try {
            clientDto = ApiHolder.clientApi.getMe();
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }
}
