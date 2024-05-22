package ru.kazenin.cherry.common.entity;

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
    @JoinColumn(name = "clientUuid", nullable = false)
    private ClientEntity client;

    @OneToMany(mappedBy = "receiptEntity")
    @Fetch(FetchMode.JOIN)
    private List<ReceiptItemEntity> receiptItems;
}
