package com.kpi.lab4.dto;

import com.kpi.lab4.enums.RoomType;

import java.util.Date;

public class CreateRequestDto {
    private int seatNumber;
    private RoomType type;
    private String phone;
    private Date dateFrom;
    private Date dateTo;

    public CreateRequestDto(int seatNumber, RoomType type, String phone, Date dateFrom, Date dateTo) {
        this.seatNumber = seatNumber;
        this.type = type;
        this.phone = phone;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public CreateRequestDto() {

    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public RoomType getType() {
        return type;
    }

    public String getPhone() {
        return phone;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }
}
