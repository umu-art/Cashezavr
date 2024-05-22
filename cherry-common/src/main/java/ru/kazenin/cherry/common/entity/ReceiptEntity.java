package ru.kazenin.cherry.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.OffsetDateTime;
import java.util.List;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "receipt")
public class ReceiptEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "client_username", referencedColumnName = "username", nullable = false)
    private ClientEntity client;

    @Column(name = "qr", unique = true, nullable = false)
    private String qr;

    @OneToMany(mappedBy = "receiptEntity")
    @Fetch(FetchMode.JOIN)
    private List<ReceiptItemEntity> receiptItems;

    @Column(name = "outside_loaded")
    private OffsetDateTime outsideLoaded;

    @Column(name = "calculated")
    private OffsetDateTime calculated;

    @Column(name = "return_sum")
    private double returnSum;

    @Column(name = "in_bill")
    private OffsetDateTime inBill;

    @ManyToOne
    @JoinColumn(name = "bill_uuid")
    private BillEntity billEntity;

}
