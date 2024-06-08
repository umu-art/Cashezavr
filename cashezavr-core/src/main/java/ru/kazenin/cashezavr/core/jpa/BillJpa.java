package ru.kazenin.cashezavr.core.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kazenin.cashezavr.cashezavr.entity.BillEntity;
import ru.kazenin.cashezavr.cashezavr.entity.ClientEntity;

import java.util.List;
import java.util.UUID;

@Repository
public interface BillJpa extends JpaRepository<BillEntity, UUID> {
    List<BillEntity> findAllByClient(ClientEntity client);
}
