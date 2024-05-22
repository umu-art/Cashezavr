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
@Table(name = "receiptItem")
public class ReceiptItemEntity extends BaseEntity {

    @Column(name = "productName", nullable = false)
    private String productName;

    @Column(name = "productCount", nullable = false)
    private int productCount;

    @Column(name = "productPrice", nullable = false)
    private double productPrice;

    @ManyToOne
    @JoinColumn(name = "receiptUuid", nullable = false)
    private ReceiptEntity receiptEntity;
}
