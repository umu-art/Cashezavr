package ru.kazenin.cherry.core.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kazenin.cherry.common.entity.ReceiptItemEntity;

import java.util.UUID;

@Repository
public interface ReceiptItemJpa extends JpaRepository<ReceiptItemEntity, UUID> {

}
