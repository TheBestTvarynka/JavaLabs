package kpi.java.dto;

import kpi.java.entity.Room;
import lombok.AllArgsConstructor;

import java.util.Date;

@AllArgsConstructor
public class CreateOrderDto {
    public Date dateFrom;
    public Date dateTo;
    public String roomNumber;
    public String phone;
    public Room room;

    public CreateOrderDto(Date dateFrom, Date dateTo, String roomNumber, String phone) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.roomNumber = roomNumber;
        this.phone = phone;
    }
}
