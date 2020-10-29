package kpi.java.entity;

import kpi.java.enums.RoomType;
import lombok.Getter;

import java.util.Date;
import java.util.UUID;

@Getter
public class Order {
    private UUID id;
    private String roomNumber;
    private Date creationDate;
    private float price;
    private RoomType type;
    private Date dateFrom;
    private Date dateTo;
}
