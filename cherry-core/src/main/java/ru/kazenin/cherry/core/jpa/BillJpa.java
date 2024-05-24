package ru.kazenin.cherry.core.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kazenin.cherry.common.entity.BillEntity;

import java.util.UUID;

@Repository
public interface BillJpa extends JpaRepository<BillEntity, UUID> {

}
