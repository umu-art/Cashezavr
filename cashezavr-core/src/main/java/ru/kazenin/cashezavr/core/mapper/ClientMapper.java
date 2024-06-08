package ru.kazenin.cashezavr.core.mapper;

import org.mapstruct.Mapper;
import ru.kazenin.cashezavr.common.entity.ClientEntity;
import ru.kazenin.model.ClientDto;
import ru.kazenin.model.RegisterDto;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientEntity toEntity(RegisterDto registerDto);

    ClientDto toDto(ClientEntity clientEntity);
}
