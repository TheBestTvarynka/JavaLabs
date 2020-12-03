package com.kpi.lab4.entities;

import com.kpi.lab4.enums.RoomType;

import java.util.Date;
import java.util.UUID;

public class Request {
    private UUID id;
    private int seatNumber;
    private RoomType type;
    private Date dateFrom;
    private Date dateTo;
    private String phone;

    public Request(UUID id, int seatNumber, RoomType type, Date dateFrom, Date dateTo, String phone) {
        this.id = id;
        this.seatNumber = seatNumber;
        this.type = type;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.phone = phone;
    }

    public UUID getId() {
        return id;
    }

    public int getSeatNumber() {
        return seatNumber;
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

    public String getPhone() {
        return phone;
    }
}
