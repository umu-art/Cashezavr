package ru.kazenin.cashezavr.core.service;

import ru.kazenin.model.BillDto;
import ru.kazenin.model.BillRequireDto;

import java.util.List;

public interface BillService {
    List<BillDto> getAllBills();

    void requireBill(BillRequireDto billRequireDto);
}
