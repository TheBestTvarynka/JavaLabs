package com.kpi.lab4.dto;

import com.kpi.lab4.entities.Room;

import java.util.Date;

public class CreateOrderDto {
    private Date dateFrom;
    private Date dateTo;
    private String roomNumber;
    private String phone;
    private Room room;

    public CreateOrderDto() {

    }

    public CreateOrderDto(Date dateFrom, Date dateTo, String roomNumber, String phone, Room room) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.roomNumber = roomNumber;
        this.phone = phone;
        this.room = room;
    }

    public CreateOrderDto(Date dateFrom, Date dateTo, String roomNumber, String phone) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.roomNumber = roomNumber;
        this.phone = phone;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getPhone() {
        return phone;
    }

    public Room getRoom() {
        return room;
    }
}
