package ru.kazenin.cherry.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.OffsetDateTime;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "promotion")
public class PromotionEntity extends BaseEntity {

    @Column(name = "starts")
    private OffsetDateTime starts;

    @Column(name = "ends")
    private OffsetDateTime ends;

    @Column(name = "percent")
    private double percent;

    @Column(name = "product")
    private String product;
}
