package ru.kazenin.cherry.outside.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kazenin.cherry.common.entity.PromotionEntity;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface PromotionJpa extends JpaRepository<PromotionEntity, UUID> {
    List<PromotionEntity> findAllByProductAndStartsBeforeAndEndsBefore(String productName, OffsetDateTime now1, OffsetDateTime now2);
}
