package ru.kazenin.cherry.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.OffsetDateTime;
import java.util.UUID;

import static java.util.Objects.isNull;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "uuid", nullable = false)
    private UUID uuid;

    @Column(name = "createdTime", nullable = false)
    private OffsetDateTime createdTime;

    @Column(name = "updatedTime")
    private OffsetDateTime updatedTime;

    @PrePersist
    public void prePersist() {
        if (isNull(this.createdTime)) {
            this.createdTime = OffsetDateTime.now();
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedTime = OffsetDateTime.now();
    }
}
