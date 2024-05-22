package ru.kazenin.cherry.outside.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kazenin.cherry.common.entity.ReceiptEntity;

import java.util.UUID;

@Repository
public interface ReceiptJpa extends JpaRepository<ReceiptEntity, UUID> {

}
