package ru.kazenin.cashezavr.core.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kazenin.cashezavr.common.entity.PromotionEntity;

import java.util.UUID;

@Repository
public interface PromotionJpa extends JpaRepository<PromotionEntity, UUID> {

}
