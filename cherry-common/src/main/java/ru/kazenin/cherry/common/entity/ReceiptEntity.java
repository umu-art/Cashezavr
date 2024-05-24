package ru.kazenin.cherry.common.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "client_username", referencedColumnName = "username", nullable = false)
    private ClientEntity client;

    @Column(name = "qr", unique = true, nullable = false)
    private String qr;

    @OneToMany(mappedBy = "receiptEntity", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<ReceiptItemEntity> receiptItems;

    @Column(name = "loaded")
    private OffsetDateTime loaded;

    @Column(name = "return_sum")
    private double returnSum;

    @Column(name = "load_lock")
    private boolean loadLock;

}
