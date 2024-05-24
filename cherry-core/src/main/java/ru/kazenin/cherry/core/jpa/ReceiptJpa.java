package ru.kazenin.cherry.core.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kazenin.cherry.common.entity.ClientEntity;
import ru.kazenin.cherry.common.entity.ReceiptEntity;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReceiptJpa extends JpaRepository<ReceiptEntity, UUID> {

    boolean existsByQr(String qr);

    int countByUuidAndLoadedNotNull(UUID uuid);

    List<ReceiptEntity> findAllByClient(ClientEntity user);
}
