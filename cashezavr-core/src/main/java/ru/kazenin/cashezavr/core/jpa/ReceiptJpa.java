package ru.kazenin.cashezavr.core.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kazenin.cashezavr.cashezavr.entity.ClientEntity;
import ru.kazenin.cashezavr.cashezavr.entity.ReceiptEntity;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReceiptJpa extends JpaRepository<ReceiptEntity, UUID> {

    boolean existsByQr(String qr);

    int countByUuidAndLoadedNotNull(UUID uuid);

    List<ReceiptEntity> findAllByClientOrderByDate(ClientEntity user);
}
