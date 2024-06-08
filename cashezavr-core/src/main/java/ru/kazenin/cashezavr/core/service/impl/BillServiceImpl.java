package ru.kazenin.cashezavr.core.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kazenin.cashezavr.cashezavr.dto.BillStatus;
import ru.kazenin.cashezavr.cashezavr.entity.BillEntity;
import ru.kazenin.cashezavr.core.jpa.BillJpa;
import ru.kazenin.cashezavr.core.mapper.BillMapper;
import ru.kazenin.cashezavr.core.service.BillService;
import ru.kazenin.cashezavr.core.service.ContextHolder;
import ru.kazenin.model.BillDto;
import ru.kazenin.model.BillRequireDto;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {

    private final ContextHolder contextHolder;
    private final BillJpa billJpa;
    private final BillMapper billMapper;

    @Override
    public List<BillDto> getAllBills() {
        var entities = billJpa.findAllByClient(contextHolder.getCurrentClient());
        return billMapper.toDto(entities);
    }

    @Override
    @Transactional
    public void requireBill(BillRequireDto billRequireDto) {
        double returnSum = billRequireDto.getSum().doubleValue();

        if (!isFinOk(returnSum)) {
            throw new RuntimeException("wtf");
        }

        var client = contextHolder.getCurrentClient();
        var bill = new BillEntity();
        bill.setClient(client);
        bill.setSum(returnSum);
        bill.setStatus(BillStatus.WAITING);

        client.setActualBalance(client.getActualBalance() - returnSum);

        billJpa.saveAndFlush(bill);
    }

    private boolean isFinOk(double sum) {
        var client = contextHolder.getCurrentClient();

        if (client.getActualBalance() + 0.01 < sum) {
            return false;
        }

        var returnedAllTime = client.getAllBalance() - (client.getActualBalance() - sum);
        if (returnedAllTime > 5000.01) {
            return false;
        }

        return true;
    }
}
