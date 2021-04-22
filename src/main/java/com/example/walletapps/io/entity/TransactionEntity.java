package com.example.walletapps.io.entity;

import com.example.walletapps.shared.dto.WalletsDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactionTBL")
@SequenceGenerator(name = "seqTRANS", initialValue =100, allocationSize = 10)
public class TransactionEntity implements Serializable {
    private static final long serialVersionUID = -1515287753272748781L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqTRANS")
    private long id;

    @Column(nullable = false)
    private String transactionId;

    @Column(nullable = true)
    private String name;

    @Column(nullable = false)
    private long amount;

    @Column(nullable = false)
    private LocalDateTime tanggal;

    private String note;

    @Column(name = "is_deleted")
    private boolean isDeleted = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wallet_id")
    private WalletsEntity walletsEntity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTransaction_id() {
        return transactionId;
    }

    public void setTransaction_id(String transaction_id) {
        this.transactionId = transaction_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public LocalDateTime getTanggal() {
        return tanggal;
    }

    public void setTanggal(LocalDateTime tanggal) {
        this.tanggal = tanggal;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public WalletsEntity getWalletsDTO() {
        return walletsEntity;
    }

    public void setWalletsEntity(WalletsEntity walletsEntity) {
        this.walletsEntity = walletsEntity;
    }
}
