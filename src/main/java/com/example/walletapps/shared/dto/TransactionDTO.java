package com.example.walletapps.shared.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class TransactionDTO implements Serializable {
    private static final long serialVersionUID = -7695921546456713716L;

    private long id;

    private String transaction_id;

    private String name;

    private long amount;

    private LocalDateTime tanggal;

    private String note;

    private boolean isDeleted;

    private WalletsDTO walletsDTO;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
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

    public WalletsDTO getWalletsDTO() {
        return walletsDTO;
    }

    public void setWalletsDTO(WalletsDTO walletsDTO) {
        this.walletsDTO = walletsDTO;
    }
}
