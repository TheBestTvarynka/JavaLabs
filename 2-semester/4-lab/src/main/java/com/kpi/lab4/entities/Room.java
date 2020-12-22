package com.kpi.lab4.entities;

import com.kpi.lab4.enums.RoomStatus;
import com.kpi.lab4.enums.RoomType;

import java.util.UUID;

public class Room {
    private UUID id;
    private String number;
    private int seatNumber;
    private RoomType type;
    private RoomStatus status;
    private float price;

    public Room() {

    }

    public Room(UUID id, String number, int seatNumber, RoomType type, RoomStatus status, float price) {
        this.id = id;
        this.number = number;
        this.seatNumber = seatNumber;
        this.type = type;
        this.status = status;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public RoomType getType() {
        return type;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public float getPrice() {
        return price;
    }
}
