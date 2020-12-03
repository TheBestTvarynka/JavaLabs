package com.kpi.lab4.entities;

import com.kpi.lab4.enums.RoomType;

import java.util.Date;
import java.util.UUID;

public class Order {
    private UUID id;
    private String roomNumber;
    private Date creationDate;
    private float price;
    private RoomType type;
    private Date dateFrom;
    private Date dateTo;
    private boolean paid;

    public Order(UUID id, String roomNumber, Date creationDate, float price, RoomType type, Date dateFrom, Date dateTo, boolean paid) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.creationDate = creationDate;
        this.price = price;
        this.type = type;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.paid = paid;
    }

    public Order(UUID id, String roomNumber, Date creationDate, float price, RoomType type, Date dateFrom, Date dateTo) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.creationDate = creationDate;
        this.price = price;
        this.type = type;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public UUID getId() {
        return id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public float getPrice() {
        return price;
    }

    public RoomType getType() {
        return type;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public boolean getPaid() {
        return paid;
    }
}
