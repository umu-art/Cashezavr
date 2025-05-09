package ru.kazenin.cashezavr.outside.mapper;

import org.mapstruct.Mapper;
import ru.kazenin.cashezavr.common.entity.ReceiptEntity;
import ru.kazenin.model.ReceiptDto;

@Mapper(componentModel = "spring")
public interface ReceiptMapper {
    ReceiptEntity toEntity(ReceiptDto receiptDto);
}
