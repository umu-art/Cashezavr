package ru.kazenin.cashezavr.outside.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kazenin.cashezavr.common.entity.ReceiptEntity;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReceiptJpa extends JpaRepository<ReceiptEntity, UUID> {
    List<ReceiptEntity> findAllByLoadedNullAndLoadLockFalse();
}
