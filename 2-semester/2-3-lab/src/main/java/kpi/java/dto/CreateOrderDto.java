package kpi.java.dto;

import kpi.java.entity.Room;

import java.util.Date;

public class CreateOrderDto {
    public Date dateFrom;
    public Date dateTo;
    public String roomNumber;
    public Room room;

    public CreateOrderDto(Date dateFrom, Date dateTo, String roomNumber) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.roomNumber = roomNumber;
    }
}
