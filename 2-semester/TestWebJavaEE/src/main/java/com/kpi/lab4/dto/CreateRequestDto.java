package com.kpi.lab4.dto;

import com.kpi.lab4.enums.RoomType;

import java.util.Date;

public class CreateRequestDto {
    public int seatNumber;
    public RoomType type;
    public String phone;
    public Date dateFrom;
    public Date dateTo;

    public CreateRequestDto(int seatNumber, RoomType type, String phone, Date dateFrom, Date dateTo) {
        this.seatNumber = seatNumber;
        this.type = type;
        this.phone = phone;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }
}
