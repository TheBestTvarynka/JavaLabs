package kpi.java.entity;

import kpi.java.enums.RoomType;
import lombok.Getter;

import java.util.Date;
import java.util.UUID;

@Getter
public class Request {
    private UUID id;
    private int seatNumber;
    private RoomType type;
    private Date dateFrom;
    private Date dateTo;
}
