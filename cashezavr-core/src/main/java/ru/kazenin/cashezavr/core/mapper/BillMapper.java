package ru.kazenin.cashezavr.core.mapper;

import org.mapstruct.Mapper;
import ru.kazenin.cashezavr.cashezavr.entity.BillEntity;
import ru.kazenin.model.BillDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BillMapper {
    List<BillDto> toDto(List<BillEntity> billEntities);
}
