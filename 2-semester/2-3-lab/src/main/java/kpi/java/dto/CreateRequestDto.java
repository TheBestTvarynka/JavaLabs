package kpi.java.dto;

import kpi.java.enums.RoomType;
import lombok.AllArgsConstructor;

import java.util.Date;

@AllArgsConstructor
public class CreateRequestDto {
    public int seatNumber;
    public RoomType type;
    public Date dateFrom;
    public Date dateTo;
}
