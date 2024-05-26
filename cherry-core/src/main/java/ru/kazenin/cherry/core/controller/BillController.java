package ru.kazenin.cherry.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import ru.kazenin.api.BillApi;
import ru.kazenin.cherry.core.service.BillService;
import ru.kazenin.model.BillDto;
import ru.kazenin.model.BillRequireDto;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BillController implements BillApi {

    private final BillService billService;

    @Override
    public ResponseEntity<List<BillDto>> getAllBills() {
        return ResponseEntity.ok(billService.getAllBills());
    }

    @Override
    public ResponseEntity<Void> requireBill(BillRequireDto billRequireDto) {
        billService.requireBill(billRequireDto);
        return ResponseEntity.ok().build();
    }
}
