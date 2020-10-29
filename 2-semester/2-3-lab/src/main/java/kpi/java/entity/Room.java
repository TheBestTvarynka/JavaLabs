package kpi.java.entity;

import kpi.java.enums.RoomStatus;
import kpi.java.enums.RoomType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@Data
@ToString
@AllArgsConstructor
public class Room {
    private UUID id;
    private String number;
    private int seatNumber;
    private RoomType type;
    private RoomStatus status;
    private float price;
}
