package ru.kazenin.cherry.outside.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import ru.kazenin.cherry.outside.jpa.ReceiptJpa;
import ru.kazenin.cherry.outside.mapper.ReceiptMapper;
import ru.kazenin.cherry.outside.service.ReceiptLoadService;
import ru.kazenin.cherry.outside.service.ReceiptService;
import ru.kazenin.model.ReceiptDto;
import ru.kazenin.model.ReceiptRequestDto;

@Service
@RequiredArgsConstructor
public class ReceiptServiceImpl implements ReceiptService {

    private final ReceiptLoadService receiptLoadService;
    private final ReceiptMapper receiptMapper;
    private final ReceiptJpa receiptJpa;


    /**
     * Выполняет выгрузку (см ReceiptLoadService) и сохранение в бд данных о чеке.
     *
     * @param requestDto данные для выгрузки
     * @return данные чека
     */
    @Override
    public ReceiptDto loadAndSave(@NonNull ReceiptRequestDto requestDto) {
        var dto = receiptLoadService.load(requestDto);
        var entity = receiptMapper.toEntity(dto);
        receiptJpa.save(entity);
        return dto;
    }
}
