package ru.kazenin.cherry.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "receipt_item")
public class ReceiptItemEntity extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "count", nullable = false)
    private int count;

    @Column(name = "price", nullable = false)
    private double price;

    @ManyToOne
    @JoinColumn(name = "receipt_uuid", nullable = false)
    private ReceiptEntity receiptEntity;
}
