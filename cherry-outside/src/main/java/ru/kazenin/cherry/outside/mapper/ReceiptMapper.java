package ru.kazenin.cherry.outside.mapper;

import org.mapstruct.Mapper;
import ru.kazenin.cherry.common.entity.ReceiptEntity;
import ru.kazenin.model.ReceiptDto;

@Mapper(componentModel = "spring")
public interface ReceiptMapper {
    ReceiptEntity toEntity(ReceiptDto receiptDto);
}
