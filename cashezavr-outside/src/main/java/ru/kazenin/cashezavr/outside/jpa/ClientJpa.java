package ru.kazenin.cashezavr.outside.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kazenin.cashezavr.common.entity.ClientEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientJpa extends JpaRepository<ClientEntity, UUID> {
    Optional<ClientEntity> findByUsername(String username);
}
