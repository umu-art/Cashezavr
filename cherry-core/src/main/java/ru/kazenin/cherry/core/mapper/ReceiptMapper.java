package ru.kazenin.cherry.core.mapper;

import org.mapstruct.Mapper;
import ru.kazenin.cherry.common.entity.ReceiptEntity;
import ru.kazenin.model.ReceiptDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReceiptMapper {
    ReceiptEntity toEntity(ReceiptDto receiptDto);

    ReceiptDto toDto(ReceiptEntity receiptEntity);

    List<ReceiptDto> toDto(List<ReceiptEntity> receiptEntities);
}
